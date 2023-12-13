package by.dorogokupets.cvservice.controller;

import by.dorogokupets.cvservice.domain.dto.TestResultDto;
import by.dorogokupets.cvservice.domain.model.TestResult;
import by.dorogokupets.cvservice.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static by.dorogokupets.cvservice.controller.RequestAttributeName.*;

@Controller
public class TestResultController {
  private final TestResultService testResultService;

  @Autowired
  public TestResultController(TestResultService testResultService) {
    this.testResultService = testResultService;
  }

  @GetMapping("/cv-service/test-results")
  public String showTestResults(
          @RequestParam(defaultValue = "1") int page,
          @RequestParam(defaultValue = "testResultId") String sortBy,
          @RequestParam(defaultValue = "ASC") String sortDirection,
          Model model
  ) {
    Page<TestResult> testPage = testResultService.findAll(page -1, 8, sortBy, sortDirection);
    model.addAttribute(TEST_RESULT_PAGE, testPage);
    return "test-results";
  }

  @GetMapping("/cv-service/test-result/edit/{id}")
  public String showEditForm(@PathVariable("id") Long testResultId, Model model) {
    TestResultDto testResultDto = testResultService.findTestResultDtoById(testResultId);
    model.addAttribute(TEST_RESULT_DTO, testResultDto);
    return "edit-test-result";
  }

  @PostMapping("/cv-service/test-result/update")
  public String updateTestResult(@ModelAttribute TestResultDto testResultDto, RedirectAttributes redirectAttributes) {
    testResultService.update(testResultDto);
    redirectAttributes.addFlashAttribute(MESSAGE, "Test result (№ = " + testResultDto.getTestResultId() + ") has been update successfully");
    return "redirect:/cv-service/test-results";
  }

  @GetMapping("/cv-service/test-result/new")
  public String showCreateForm(Model model) {
    model.addAttribute(TEST_RESULT_DTO, new TestResultDto());
    return "test-result-form";
  }

  @PostMapping("/cv-service/test-result/save")
  public String saveTestResult(@ModelAttribute TestResultDto testResultDto, RedirectAttributes redirectAttributes) {
    testResultService.save(testResultDto);
    redirectAttributes.addFlashAttribute(MESSAGE, "Test result has been add successfully");
    return "redirect:/cv-service/test-results";
  }
}

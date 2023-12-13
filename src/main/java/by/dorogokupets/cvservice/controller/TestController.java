package by.dorogokupets.cvservice.controller;

import by.dorogokupets.cvservice.domain.dto.TestDto;
import by.dorogokupets.cvservice.domain.model.Test;
import by.dorogokupets.cvservice.service.DirectionService;
import by.dorogokupets.cvservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static by.dorogokupets.cvservice.controller.RequestAttributeName.*;

@Controller
public class TestController {

  private final TestService testService;
  private final DirectionService directionService;


  @Autowired
  public TestController(TestService testService, DirectionService directionService) {
    this.testService = testService;
    this.directionService = directionService;
  }

  @GetMapping("/cv-service/tests")
  public String showTests(
          @RequestParam(defaultValue = "1") int page,
          @RequestParam(defaultValue = "title") String sortBy,
          @RequestParam(defaultValue = "ASC") String sortDirection,
          Model model
  ) {
    Page<Test> testPage = testService.findAll(page - 1, 8, sortBy, sortDirection);
    model.addAttribute(TEST_PAGE, testPage);
    return "tests";
  }


  @GetMapping("/cv-service/test/edit/{id}")
  public String showEditForm(@PathVariable("id") Long testId, Model model) {
    TestDto testDto = testService.findTestDtoById(testId);
    model.addAttribute(TEST_DTO, testDto);
    model.addAttribute("possibleDirection", directionService.findAll());

    return "edit-test";
  }

  @PostMapping("/cv-service/test/update")
  public String updateTest(@ModelAttribute TestDto testDto, RedirectAttributes redirectAttributes) {
    testService.update(testDto);
    redirectAttributes.addFlashAttribute(MESSAGE, "Test has been update successfully");
    return "redirect:/cv-service/tests";
  }

  @GetMapping("/cv-service/test/new")
  public String showCreateForm(Model model) {
    model.addAttribute(TEST_DTO, new TestDto());
    model.addAttribute("possibleDirection", directionService.findAll());
    return "test-form";
  }

  @PostMapping("/cv-service/test/save")
  public String saveTest(@ModelAttribute TestDto testDto, RedirectAttributes redirectAttributes) {
    testService.save(testDto);
    redirectAttributes.addFlashAttribute(MESSAGE, "Test has been add successfully");
    return "redirect:/cv-service/tests";
  }
}

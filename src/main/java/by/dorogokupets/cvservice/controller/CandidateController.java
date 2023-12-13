package by.dorogokupets.cvservice.controller;

import by.dorogokupets.cvservice.domain.dto.CandidateDto;
import by.dorogokupets.cvservice.exception.ServiceException;
import by.dorogokupets.cvservice.domain.model.Candidate;
import by.dorogokupets.cvservice.service.CandidateService;
import by.dorogokupets.cvservice.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static by.dorogokupets.cvservice.controller.RequestAttributeName.*;

@Controller
public class CandidateController {

  private final CandidateService candidateService;
  private final DirectionService directionService;

  @Autowired
  public CandidateController(CandidateService candidateService, DirectionService directionService) {
    this.candidateService = candidateService;
    this.directionService = directionService;
  }

  @GetMapping("/cv-service/candidates")
  public String showCandidates(
          @RequestParam(defaultValue = "1") int page,
          @RequestParam(defaultValue = "lastName") String sortBy,
          @RequestParam(defaultValue = "ASC") String sortDirection,
          Model model
  ) {
    Page<Candidate> candidatePage = candidateService.findAll(page - 1, 8, sortBy, sortDirection);
    model.addAttribute(CANDIDATE_PAGE, candidatePage);
    return "candidates";
  }

  @GetMapping("cv-service/candidate/delete/{id}")
  public String deleteCandidate(@PathVariable("id") Long candidateId, RedirectAttributes redirectAttributes) {
    candidateService.delete(candidateId);
    redirectAttributes.addFlashAttribute(MESSAGE, "Candidate (id=" + candidateId + ") has been successfully deleted");
    return "redirect:/client/notices";
  }

  @GetMapping("/cv-service/candidate/new")
  public String showCreateForm(Model model) {
    model.addAttribute(CANDIDATE_DTO, new CandidateDto());
    model.addAttribute("possibleDirections", directionService.findAll());
    return "candidate-form";
  }

  @PostMapping(path = "/cv-service/candidate/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public String saveCandidate(RedirectAttributes redirectAttributes, @ModelAttribute CandidateDto candidateDTO) throws ServiceException {
    candidateService.save(candidateDTO);
    redirectAttributes.addFlashAttribute(MESSAGE, "Candidate has been add successfully");
    return "redirect:/cv-service/candidates";
  }

  @GetMapping("/cv-service/candidate/edit/{id}")
  public String showEditForm(@PathVariable("id") Long candidateId, Model model) {
    CandidateDto candidateDTO = candidateService.findCandidateDtoById(candidateId);
    model.addAttribute(CANDIDATE_DTO, candidateDTO);
    model.addAttribute("possibleDirections", directionService.findAll());
    return "edit-candidate";
  }

  @PostMapping(path = "/cv-service/candidates/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public String updateCandidate(@ModelAttribute CandidateDto candidateDTO, RedirectAttributes redirectAttributes) throws ServiceException {
    candidateService.update(candidateDTO);
    redirectAttributes.addFlashAttribute(MESSAGE, "Candidate has been update successfully");
    return "redirect:/cv-service/candidates";
  }

}

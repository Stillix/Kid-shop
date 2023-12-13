package by.dorogokupets.kidshop.controller;

import by.dorogokupets.kidshop.domain.dto.DirectionDto;
import by.dorogokupets.kidshop.domain.model.Direction;
import by.dorogokupets.kidshop.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static by.dorogokupets.kidshop.controller.RequestAttributeName.*;

@Controller
public class DirectionController {
  public static final String DIRECTION_DTO = "directionDto";
  private final DirectionService directionService;

  @Autowired
  public DirectionController(DirectionService directionService) {
    this.directionService = directionService;
  }


  @GetMapping("/cv-service/directions")
  public String showDirections(
          @RequestParam(defaultValue = "1") int page,
          @RequestParam(defaultValue = "name") String sortBy,
          @RequestParam(defaultValue = "ASC") String sortDirection,
          Model model
  ) {
    Page<Direction> directionPage = directionService.findAll(page - 1, 5, sortBy, sortDirection);
    model.addAttribute(DIRECTION_PAGE, directionPage);
    return "directions";
  }

  @GetMapping("/cv-service/direction/new")
  public String showCreateForm(Model model) {
    model.addAttribute(DIRECTION_DTO, new DirectionDto());
    return "direction-form";
  }

  @PostMapping("/cv-service/direction/save")
  public String saveDirection(@ModelAttribute DirectionDto directionDto, RedirectAttributes redirectAttributes) {
    directionService.save(directionDto);
    redirectAttributes.addFlashAttribute(MESSAGE, "Direction has been add successfully");
    return "redirect:/cv-service/directions";
  }

  @GetMapping("/cv-service/direction/edit/{id}")
  public String showEditForm(@PathVariable("id") Long candidateId, Model model) {
    DirectionDto directionDto = directionService.findDirectionDtoById(candidateId);
    model.addAttribute(DIRECTION_DTO, directionDto);
    return "edit-direction";
  }

  @PostMapping("/cv-service/direction/update")
  public String updateDirection(@ModelAttribute DirectionDto directionDto, RedirectAttributes redirectAttributes) {
    directionService.update(directionDto);
    redirectAttributes.addFlashAttribute(MESSAGE, "Direction has been update successfully");

    return "redirect:/cv-service/directions";
  }
}

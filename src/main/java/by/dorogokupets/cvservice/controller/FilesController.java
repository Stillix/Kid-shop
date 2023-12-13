package by.dorogokupets.cvservice.controller;

import by.dorogokupets.cvservice.domain.model.Candidate;
import by.dorogokupets.cvservice.domain.model.FileDB;
import by.dorogokupets.cvservice.service.CandidateService;
import by.dorogokupets.cvservice.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cv-service")
public class FilesController {

  private final FilesService filesService;
  private final CandidateService candidateService;

  @Autowired
  public FilesController(FilesService filesService, CandidateService candidateService) {
    this.candidateService = candidateService;
    this.filesService = filesService;
  }


  @GetMapping("/download-file/{id}")
  public ResponseEntity<byte[]> getPdfFile(@PathVariable("id") Long candidateId) {
    Candidate candidate = candidateService.findCandidateById(candidateId);
    FileDB file = filesService.findByCandidateAndContentType(candidate, MediaType.APPLICATION_PDF_VALUE);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
            .contentType(MediaType.APPLICATION_PDF)
            .body(file.getData());
  }

  @GetMapping(path = "/images/{id}")
  public ResponseEntity downloadImage(@PathVariable("id") Long candidateId) {
    Candidate candidate = candidateService.findCandidateById(candidateId);
    FileDB file = filesService.findByCandidateAndContentType(candidate, MediaType.IMAGE_PNG_VALUE);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
            .contentType(MediaType.IMAGE_PNG)
            .body(file.getData());
  }
}

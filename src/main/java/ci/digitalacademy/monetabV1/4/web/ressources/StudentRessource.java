package ci.digitalacademy.MonEtabV14.web.ressources;

import ci.digitalacademy.MonEtabV14.services.StudentService;
import ci.digitalacademy.MonEtabV14.services.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/students")
public class StudentRessource {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO studentDTO) {
        log.debug("REST, Request to save student : {}", studentDTO);
        return new ResponseEntity<>(studentService.save(studentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public StudentDTO update(@RequestBody StudentDTO studentDTO, @PathVariable Long id) {
        log.debug("REST, Request to update student : {}", studentDTO);
        return studentService.update(studentDTO, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        log.debug("REST, Request to get student : {}", id);
        Optional<StudentDTO> studentDTO = studentService.findOne(id);
        if (studentDTO.isPresent()) {
            return new ResponseEntity<>(studentDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        log.debug("REST Request to get all students");
        return studentService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.debug("REST Request to delete student : {}", id);
        studentService.delete(id);
    }
}

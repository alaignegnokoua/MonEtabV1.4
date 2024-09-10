package ci.digitalacademy.MonEtabV14.web.ressources;

import ci.digitalacademy.MonEtabV14.services.TeacherService;
import ci.digitalacademy.MonEtabV14.services.dto.TeacherDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherRessource {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherDTO> save(@RequestBody TeacherDTO teacherDTO) {
        log.debug("REST Request to save Teacher : {}", teacherDTO);
        return new ResponseEntity<>(teacherService.save(teacherDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TeacherDTO teacherDTO, @PathVariable Long id) {
        log.debug("REST Request to update Teacher : {}", teacherDTO);
        return new ResponseEntity<>(teacherService.update(teacherDTO, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacher(@PathVariable Long id) {
        log.debug("REST Request to get Teacher : {}", id);
        Optional<TeacherDTO> teacherDTO = teacherService.findOne(id);
        if (teacherDTO.isPresent()) {
            return new ResponseEntity<>(teacherDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Teacher not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<TeacherDTO> getAllTeachers() {
        log.debug("REST Request to get all Teachers");
        return teacherService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.debug("REST Request to delete Teacher : {}", id);
        teacherService.delete(id);
    }
}

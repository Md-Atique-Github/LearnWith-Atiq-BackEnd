package online_study.study;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/course")
public class CourseController {

    @Autowired 
    private CourseRepository courseRepo;

    @PostMapping("/add-course")
    public void saveCourses(@RequestBody Course course){
        courseRepo.save(course);
    }

    @GetMapping("/getAll-course")
    public List<Course> getAllCourse(){
        List<Course> course = courseRepo.findAll();
        return course;
    }

    @GetMapping("/get-course/{id}")
    public Optional<Course> getCourse(@PathVariable Integer id){
        return courseRepo.findById(id);
    }

    @DeleteMapping("/delete-course/{id}")
    public void deleteCourse(@PathVariable Integer id){
        courseRepo.deleteById(id);
    }
    
    @DeleteMapping("/deleteAll-course")
    public void deleteAllCourse(){
        courseRepo.deleteAll();
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<String> updateById(@PathVariable Integer id, @RequestBody Course course) {
        Optional<Course> optionalCourse = courseRepo.findById(id);
        if (optionalCourse.isPresent()) {
            Course courses = optionalCourse.get();
            courses.setTitle(course.getTitle());
            course.setDescription(course.getDescription());
            courseRepo.save(course);
            return ResponseEntity.ok().body("Course Updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}

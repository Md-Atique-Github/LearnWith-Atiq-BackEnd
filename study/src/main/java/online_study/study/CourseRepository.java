package online_study.study;

import org.springframework.data.jpa.repository.JpaRepository;



public interface CourseRepository extends JpaRepository<Course , Integer> {
    
}

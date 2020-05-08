package by.bsu.easytutor.mapper;

import by.bsu.easytutor.dto.StudentDTO;
import by.bsu.easytutor.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class StudentMapper extends AbstractMapper<Student, StudentDTO> {

    private ModelMapper modelMapper;

    @Autowired
    public StudentMapper(ModelMapper modelMapper) {
        super(Student.class, StudentDTO.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void SetupMapper() {
        modelMapper.createTypeMap(Student.class, StudentDTO.class);
        modelMapper.createTypeMap(StudentDTO.class, Student.class);
    }

}

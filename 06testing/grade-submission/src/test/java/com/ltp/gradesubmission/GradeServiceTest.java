package com.ltp.gradesubmission;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

  @Mock
  private GradeRepository gradeRepository; // this will mock the dependency, while having no logic on its own.

  @InjectMocks
  private GradeService gradeService;
}

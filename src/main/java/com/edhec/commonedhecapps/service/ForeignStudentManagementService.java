package com.edhec.commonedhecapps.service;

import com.edhec.commonedhecapps.model.common.ComArtificialPerson;
import com.edhec.commonedhecapps.model.common.ComContact;
import com.edhec.commonedhecapps.model.common.ComMail;
import com.edhec.commonedhecapps.model.common.ComPhone;
import com.edhec.commonedhecapps.model.foreignstudent.*;
import com.edhec.commonedhecapps.web.foreignstudent.command.CorrespondantCommand;
import com.edhec.commonedhecapps.web.foreignstudent.command.ForeignStudentCommand;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ForeignStudentManagementService {
    public ForeignUniversity findForeignUniversityById(String foreignUniversityId);
    public ComArtificialPerson findComArtificialPersonById(String idUniversity);
    public ComMail findComContactEmail(String malId) throws DataAccessException;
    public ComPhone findComContactPhone(String phoneId) throws DataAccessException;
    public ComContact findContactById(String studentId);
    public ForeignUniversity findForeignUniversityByIdAndProgram(String foreignUniversityId, String program, String year);
    public ScoFsProgramLimit existScoFSProgramClosed(String shyId, String scoYear);
    public List<ForeignStudentView> findForeignStudentViewByUniversity(String universityId, String program, String year);
    public void updateForeignUniversityCapacity(String idUniversity, String program, String year);
    public Boolean findErasmusByLibang (String libang);
    public String createOrUpdateCorrespondantManagement(String idUniversity, String school, CorrespondantCommand command);
    public List<ForeignStudentView> findForeignStudentByUniversityAndStatus(String universityId, String crtIdStatus, String program, String year);
    public BookNivChoixIncoming findBookNivChoixIncomingbyCode (String studentLevel, String scoYear);
    public ForeignStudent findForeignStudentById(String foreignStudentId);
    public String existForeignStudentEmail(String email);
    public String existScoFSProgramLimit(String shyId,String scoYear,String campus,String levelId,String periodId);
    public void createForeignStudentAndSendMail(String foreignStudentId, String universityId, String host) throws Exception;
    public String createForeignStudent(String idUniversity, String program, String year, ForeignStudentCommand command );
    public void deleteForeignStudent(String foreignStudentId);
    public void registerForeignStudent(String foreignStudentId);
    public void confirmForeignStudent(String foreignStudentId);
    public void cancelStudent(String foreignStudentId);
}

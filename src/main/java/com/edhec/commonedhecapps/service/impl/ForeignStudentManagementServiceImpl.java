package com.edhec.commonedhecapps.service.impl;

import com.edhec.commonedhecapps.model.common.*;
import com.edhec.commonedhecapps.model.foreignstudent.*;
import com.edhec.commonedhecapps.service.ForeignStudentManagementService;
import com.edhec.commonedhecapps.web.foreignstudent.command.CorrespondantCommand;
import com.edhec.commonedhecapps.model.common.LoginOnDemand;
import com.edhec.commonedhecapps.web.foreignstudent.command.ForeignStudentCommand;
import com.edhec.mail.CommonEdhecMailSender;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ForeignStudentManagementServiceImpl implements ForeignStudentManagementService {


    private Logger logger = org.slf4j.LoggerFactory.getLogger(ForeignStudentManagementServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CommonStudentAbroadServiceImpl commonStudentAbroadService;


    public ForeignUniversity findForeignUniversityById(String foreignUniversityId) {
        javax.persistence.Query query = entityManager.createNamedQuery("ForeignUniversity.findByUniversityId").setParameter("idUniversity", foreignUniversityId);
        ForeignUniversity foreignUniversity = null;
        if(query.getResultList().size() > 0) {
            foreignUniversity = (ForeignUniversity)query.getResultList().get(0);
        }
        return foreignUniversity;
    }

    public ComArtificialPerson findComArtificialPersonById(String idUniversity) {
        javax.persistence.Query query = entityManager.createNamedQuery("ComArtificialPerson.findByCntId").setParameter("cntId", idUniversity);

        ComArtificialPerson comArtificialPerson = null;

        if(query.getResultList().size() > 0) {
            comArtificialPerson = (ComArtificialPerson)query.getResultList().get(0);
        }

        return comArtificialPerson;
    }

    public ComMail findComContactEmail(String malId) throws DataAccessException {
        if (malId==null){return null;}
        ComMail comMail = new ComMail();
        try{
            Query query = entityManager.createQuery("select o from ComMail o where o.id = :malId").setParameter("malId", malId);
            if(query.getResultList().size() > 0) {
                comMail = (ComMail)query.getResultList().get(0);}
        }catch (NoResultException e){
            logger.info("Err findCorrespondantEmail : "+ e);
            return null;
        }
        return comMail;
    }

    public ComPhone findComContactPhone(String phoneId) throws DataAccessException {
        if (phoneId==null){return null;}
        ComPhone comPhone = new ComPhone();
        try{
            Query query = entityManager.createQuery("select o from ComPhone o where o.id = :phoneId").setParameter("phoneId", phoneId);
            if(query.getResultList().size() > 0) {
                comPhone = (ComPhone)query.getResultList().get(0);}else{
                return null;
            }
        }catch (NoResultException e){
            logger.info("Err findCorrespondantPhone : "+ e);
            return null;
        }
        return comPhone;
    }

    public ComContact findContactById(String studentId) {
        Query query = entityManager.createQuery("select o from ComContact o where o.id = :idStudent").setParameter("idStudent", studentId);
        ComContact contact = null;

        if(query != null && query.getResultList().size() > 0) {
            contact = (ComContact)query.getSingleResult();
        }
        return contact;
    }

    public ForeignUniversity findForeignUniversityByIdAndProgram(String foreignUniversityId, String program, String year) {
        javax.persistence.Query query = entityManager.createNamedQuery("ForeignUniversity.findByUniversityIdAndProgram").setParameter("idUniversity", foreignUniversityId).setParameter("shyId", program).setParameter("scoYear", new BigDecimal(year));
        ForeignUniversity foreignUniversity = null;
        if(query.getResultList().size() > 0) {
            foreignUniversity = (ForeignUniversity)query.getResultList().get(0);
        }
        return foreignUniversity;
    }

    public ScoFsProgramLimit existScoFSProgramClosed(String shyId, String scoYear){
        javax.persistence.Query query = entityManager.createNamedQuery("ScoFsProgramLimit.dateLimit").setParameter("shyId", shyId).setParameter("scoYear", scoYear);
        ScoFsProgramLimit scoFsProgramLimit = null;
        if(query.getResultList().size() > 0) {
            scoFsProgramLimit = (ScoFsProgramLimit)query.getResultList().get(0);
        }
        return scoFsProgramLimit;
    }

    @SuppressWarnings("unchecked")
    public List<ForeignStudentView> findForeignStudentViewByUniversity(String universityId, String program, String year) {
        javax.persistence.Query query = entityManager.createNamedQuery("ForeignStudentView.findByUniversityId").setParameter("idUniversity", universityId).setParameter("shyId", program).setParameter("scoYear", new BigDecimal(year));
        return query.getResultList();
    }

    @Transactional
    public void updateForeignUniversityCapacity(String idUniversity, String program, String year) {

        ForeignUniversity foreignUniversity = findForeignUniversityByIdAndProgram(idUniversity, program, year);

        int nbSemester = 0;
        int nbDoubleDiploma = 0;

        if(foreignUniversity.getForeignStudentList() != null) {
            for(ForeignStudent foreignStd : foreignUniversity.getForeignStudentList()) {
                if ((foreignStd.getDateHistory() == null) && !(foreignStd.getForeignStudentView().getCrtIdStatus().equalsIgnoreCase("A"))) {
                    if (foreignStd.getDoubleDiploma()!=null && foreignStd.getDoubleDiploma().equalsIgnoreCase("Y")){
                        nbDoubleDiploma++;
                    }else{
							/*
						  if(foreignStd.getFall() != null && foreignStd.getWinter() != null && foreignStd.getSpring() != null) {
							if(foreignStd.getFall().equalsIgnoreCase("1") && foreignStd.getWinter().equalsIgnoreCase("1") && foreignStd.getSpring().equalsIgnoreCase("1")) {
								nbSemester+=2;
							}else{
								nbSemester++;
							}
						  }*/
                        if(foreignStd.getPeriodeCode() != null) {
                            if(foreignStd.getPeriodeCode().equalsIgnoreCase("S0")) {
                                nbSemester+=2;
                            }else{
                                nbSemester++;
                            }
                        }
                    }
                }
            }
        }
        foreignUniversity.setUsedDdSemester(new BigDecimal(nbDoubleDiploma));
        foreignUniversity.setUsedSemester(new BigDecimal(nbSemester));

        entityManager.merge(foreignUniversity);
        entityManager.flush();
        logger.info("Capacity updated for univ : "+idUniversity);
    }

    public Boolean findErasmusByLibang (String libang){

        javax.persistence.Query query = entityManager.createNamedQuery("ScoPaysErasmus.findErasmusByLibang").setParameter("libang", libang);
        Boolean isErasmus = false;
        if(query.getResultList().size() > 0) {
            isErasmus = true;
        }
        return isErasmus;
    }

    @Transactional
    public String createOrUpdateCorrespondantManagement(String idUniversity, String school, CorrespondantCommand command) {
        ComArtificialPerson comArtificialPerson = findComArtificialPersonById(idUniversity);
        String coordType = (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME))?ForeignUniversity.FUNCTION_COORD_ESPEME:ForeignUniversity.FUNCTION_COORD_EDHEC;
        //mise a jour du code Erasmus et de l'adresse de l'universitee pour l'annee en cours
        Calendar cal = new GregorianCalendar();
        ForeignUniversity foreignUniversity = findForeignUniversityByIdAndProgram(idUniversity,school,cal.get(Calendar.YEAR) + 1 + "");
        if (findErasmusByLibang(foreignUniversity.getCountryName())){
            foreignUniversity.setCodeErasmus(command.getCodeErasmus());
        }
        foreignUniversity.setAddress(command.getAddress());
        foreignUniversity.setZipcode(command.getZipcode());
        foreignUniversity.setTown(command.getTown());
        foreignUniversity.setCountryName(command.getCountry());
        if (command.getOla()!=null && command.getOla().contentEquals("OLA")){
            foreignUniversity.setOla("OLA");
        }else{
            foreignUniversity.setOla("NO");
        }
        entityManager.merge(foreignUniversity);

        //Check if correspondant exists


        boolean findCorrespondant = false;
        ComJob comJob = new ComJob();
        if (comArtificialPerson != null && comArtificialPerson.getComJobList() != null && comArtificialPerson.getComJobList().size() > 0){

            List<ComJob> comJobList = new ArrayList<ComJob>();
            comJobList = comArtificialPerson.getComJobList();
            boolean findComJob = false;
            for (ComJob comJobuser : comJobList){
                if (comJobuser.getDateTo()==null && comJobuser.getCrtIdFunction()!=null && comJobuser.getCrtIdFunction().equalsIgnoreCase(coordType)){
                    if (!findComJob){
                        comJob = comJobuser;
                        findComJob = true;
                    }
                }
            }

            if (findComJob){
                findCorrespondant = true;
            }
        }

        if(!(findCorrespondant)) { // No correspondant created

            // Creation du contact
            String comContactId = commonStudentAbroadService.generateId("com_genkey_contact");

            ComContact comContact = new ComContact();
            comContact.setId(comContactId);
            comContact.setName(command.getLastName());
            //comContact.set

            ComNaturalPerson comNaturalPerson = new ComNaturalPerson();
            comNaturalPerson.setComContact(comContact);
            comNaturalPerson.setCntId(comContactId);
            comNaturalPerson.setFirstName(command.getFirstName());
            comNaturalPerson.setBirthName(command.getLastName());
            comNaturalPerson.setSoftwares(command.getSoftwares());


            comContact.setComNaturalPerson(comNaturalPerson);

            entityManager.persist(comContact);
            entityManager.persist(comNaturalPerson);

            //Recherche dans com_mail si le mail existe
            ComMail email = findComContactEmailByMail(command.getEmail());
            String malId = "";
            if (email==null)
            {
                // si non, on cree le mail et on l'associe au contact
                ComMail comMail = new ComMail();
                ComContactMail comContactMail = new ComContactMail();
                comMail.setId(commonStudentAbroadService.generateId("com_genkey_mail"));
                malId = comMail.getId();
                comMail.setMail(command.getEmail());
                entityManager.persist(comMail);
                ComContactMailPK pk = new ComContactMailPK();
                pk.setCntId(comContact.getId());
                pk.setAkdId(ForeignUniversity.COORD_MAIL_AKDID);
                pk.setMalId(comMail.getId());
                comContactMail.setId(pk);
                comContactMail.setComMail(comMail);
                entityManager.persist(comContactMail);
                //comContact.setComContactMailList(new ArrayList<ComContactMail>());
                //comContact.getComContactMailList().add(comContactMail);
            }
            else{
                //Si oui, on associe le mail au nouveau contact
                malId = email.getId();
                ComContactMail comContactMail = new ComContactMail();
                ComContactMailPK pk = new ComContactMailPK();
                pk.setCntId(comContact.getId());
                pk.setAkdId(ForeignUniversity.COORD_MAIL_AKDID);
                pk.setMalId(email.getId());
                comContactMail.setId(pk);
                comContactMail.setComMail(email);
                entityManager.persist(comContactMail);
                //comContact.setComContactMailList(new ArrayList<ComContactMail>());
                //comContact.getComContactMailList().add(comContactMail);
            }

            //Recherche dans com_phone si le phone existe
            ComPhone phone = findComContactPhone(command.getPhone());
            String phoneId = "";
            if (phone==null)
            {
                // si non, on cree le phone et on l'associe au contact
                ComPhone comPhone = new ComPhone();
                ComContactPhone comContactPhone = new ComContactPhone();
                comPhone.setId(commonStudentAbroadService.generateId("com_genkey_mail"));
                phoneId = comPhone.getId();
                comPhone.setPhone(command.getPhone());
                entityManager.persist(comPhone);
                ComContactPhonePK pk = new ComContactPhonePK();
                pk.setCntId(comContact.getId());
                pk.setAkdId(ForeignUniversity.COORD_PHONE_AKDID);
                pk.setKopId(ForeignUniversity.COORD_PHONE_KOPID);
                pk.setPhnId(comPhone.getId());
                comContactPhone.setId(pk);
                comContactPhone.setComPhone(comPhone);
                entityManager.persist(comContactPhone);
                //comContact.setComContactPhoneList(new ArrayList<ComContactPhone>());
                //comContact.getComContactPhoneList().add(comContactPhone);
            }
            else{
                //Si oui, on associe le phone au nouveau contact
                phoneId = phone.getId();
                ComContactPhone comContactPhone = new ComContactPhone();
                ComContactPhonePK pk = new ComContactPhonePK();
                pk.setCntId(comContact.getId());
                pk.setAkdId(ForeignUniversity.COORD_PHONE_AKDID);
                pk.setKopId(ForeignUniversity.COORD_PHONE_KOPID);
                pk.setPhnId(phone.getId());
                comContactPhone.setId(pk);
                comContactPhone.setComPhone(phone);
                entityManager.persist(comContactPhone);
                //comContact.setComContactPhoneList(new ArrayList<ComContactPhone>());
                //comContact.getComContactPhoneList().add(comContactPhone);
            }

            // Fin Creation du contact

            comJob.setDescription(command.getTitle());
            comJob.setCrtCkaIdFunction("FUNCTION");
            comJob.setCrtIdFunction(coordType);
            comJob.setMalId(malId);
            comJob.setPhnId(phoneId);

            comJob.setId(new ComJobPK());
            comJob.getId().setArpCntId(idUniversity);
            comJob.getId().setNtpCntId(comContact.getId());
            comJob.getId().setDateFrom(new Date());
            comJob.getId().setOrderBy(1);
            entityManager.persist(comJob);

            logger.info("correspondant with id : "+comContactId+" created");
            return "OK";
        }else { // Correspondants found updating
            comJob.getComNaturalPerson().setFirstName(command.getFirstName());
            comJob.getComNaturalPerson().setBirthName(command.getLastName());
            comJob.getComNaturalPerson().setSoftwares(command.getSoftwares());
            entityManager.merge(comJob.getComNaturalPerson());
            comJob.getComNaturalPerson().getComContact().setName(command.getLastName());
            entityManager.merge(comJob.getComNaturalPerson().getComContact());


            //Recherche dans com_mail si le mail existe
            ComMail comMail = findComContactEmailByMail(command.getEmail());
            //Si email non nul, on verifie que ce mail n'est pas deja associe au contact avec le type 'S'
            boolean comContactMailFind = false;
            if (comMail!=null){
                for(ComContactMail currentMail : comJob.getComNaturalPerson().getComContact().getComContactMailList()) {
                    if(currentMail.getId().getAkdId().equalsIgnoreCase(ForeignUniversity.COORD_MAIL_AKDID) && currentMail.getComMail()==comMail  ) {
                        //il est associe avec le type 'S', on ne fait rien
                        comContactMailFind=true;
                    }
                }
            }else{
                // creation d'un nouveau email
                comMail = new ComMail();
                comMail.setId(commonStudentAbroadService.generateId("com_genkey_mail"));
                comMail.setMail(command.getEmail());
                entityManager.persist(comMail);
            }

            if (!(comContactMailFind))
            {
                // On associe le mail au contact
                ComContactMail comContactMail = new ComContactMail();
                ComContactMailPK pk = new ComContactMailPK();
                pk.setCntId(comJob.getComNaturalPerson().getComContact().getId());
                pk.setAkdId(ForeignUniversity.COORD_MAIL_AKDID);
                pk.setMalId(comMail.getId());
                comContactMail.setId(pk);
                comContactMail.setComMail(comMail);
                entityManager.persist(comContactMail);
            }

            //Enregistrement du tÃ©lÃ©phone si non vide
            if (command.getPhone() !=null && !command.getPhone().trim().equalsIgnoreCase("")){
                //Recherche dans com_phone si le phone existe
                ComPhone comPhone = findComContactPhoneByPhone(command.getPhone());
                //Si phone non nul, on verifie que ce phone n'est pas deja associe au contact avec le type 'S'
                boolean comContactPhoneFind = false;
                if (comPhone!=null){
                    for(ComContactPhone currentPhone : comJob.getComNaturalPerson().getComContact().getComContactPhoneList()) {
                        if(currentPhone.getId().getAkdId().equalsIgnoreCase(ForeignUniversity.COORD_PHONE_AKDID) && currentPhone.getComPhone()==comPhone && currentPhone.getId().getKopId().equalsIgnoreCase(ForeignUniversity.COORD_PHONE_KOPID) ) {
                            //il est associe avec le type 'S', on ne fait rien
                            comContactPhoneFind=true;
                        }
                    }
                }else{
                    // creation d'un nouveau phone
                    comPhone = new ComPhone();
                    comPhone.setId(commonStudentAbroadService.generateId("com_genkey_mail"));
                    comPhone.setPhone(command.getPhone());
                    entityManager.persist(comPhone);
                }

                if (!(comContactPhoneFind))
                {
                    // On associe le phone au contact
                    ComContactPhone comContactPhone = new ComContactPhone();
                    ComContactPhonePK pk = new ComContactPhonePK();
                    pk.setCntId(comJob.getComNaturalPerson().getComContact().getId());
                    pk.setAkdId(ForeignUniversity.COORD_PHONE_AKDID);
                    pk.setKopId(ForeignUniversity.COORD_PHONE_KOPID);
                    pk.setPhnId(comPhone.getId());
                    comContactPhone.setId(pk);
                    comContactPhone.setComPhone(comPhone);
                    entityManager.persist(comContactPhone);
                }
                comJob.setPhnId(comPhone.getId());
            }

            comJob.setMalId(comMail.getId());
            comJob.setDescription(command.getTitle());
            //Ajout 2014 pour forcer les mises Ã  jour des jobs des correspondants
            comJob.setDateModified(new Date());

            entityManager.merge(comJob);
            logger.info("correspondant with id : "+comJob.getComNaturalPerson().getComContact().getId()+" updated");
            return "OK";
        }

    }

    public ComMail findComContactEmailByMail(String mail) throws DataAccessException {
        if (mail==null){return null;}
        ComMail comMail = new ComMail();
        try{
            Query query = entityManager.createQuery("select o from ComMail o where trim(upper(o.mail)) = trim(upper(:mail))").setParameter("mail", mail);
            if(query.getResultList().size() > 0) {
                comMail = (ComMail)query.getResultList().get(0);}else{
                return null;
            }
        }catch (NoResultException e){
            logger.info("Err findCorrespondantEmailByMail : "+ e);
            return null;
        }
        return comMail;
    }

    public ComPhone findComContactPhoneByPhone(String phone) throws DataAccessException {
        if (phone==null){return null;}
        ComPhone comPhone = new ComPhone();
        try{
            Query query = entityManager.createQuery("select o from ComPhone o where trim(upper(o.phone)) = trim(upper(:phone))").setParameter("phone", phone);
            if(query.getResultList().size() > 0) {
                comPhone = (ComPhone)query.getResultList().get(0);}else{
                return null;
            }
        }catch (NoResultException e){
            logger.info("Err findCorrespondantPhoneByPhone : "+ e);
            return null;
        }
        return comPhone;
    }

    @SuppressWarnings("unchecked")
    public List<ForeignStudentView> findForeignStudentByUniversityAndStatus(String universityId, String crtIdStatus, String program, String year) {
        javax.persistence.Query query = entityManager.createNamedQuery("ForeignStudentView.findByUniversityIdAndStatus").setParameter("idUniversity", universityId).setParameter("crtIdStatus", crtIdStatus).setParameter("shyId", program).setParameter("scoYear", new BigDecimal(year));
        return query.getResultList();
    }

    public BookNivChoixIncoming findBookNivChoixIncomingbyCode (String studentLevel, String scoYear){
        javax.persistence.Query query = entityManager.createNamedQuery("BookNivChoixIncoming.findByCodeWithoutSpec").setParameter("code", studentLevel).setParameter("anneeUniv", new BigDecimal(scoYear));
        BookNivChoixIncoming bookNivChoixIncoming = null;
        if(query.getResultList().size() > 0) {
            bookNivChoixIncoming = (BookNivChoixIncoming)query.getResultList().get(0);
        }
        return bookNivChoixIncoming;
    }

    public ForeignStudent findForeignStudentById(String foreignStudentId) {
        javax.persistence.Query query = entityManager.createNamedQuery("ForeignStudent.findById").setParameter("id", foreignStudentId);
        ForeignStudent foreignStudent = null;
        if(query.getResultList().size() > 0) {
            foreignStudent = (ForeignStudent)query.getResultList().get(0);
        }
        return foreignStudent;
    }

    public String existForeignStudentEmail(String email) {
        javax.persistence.Query query = entityManager.createNamedQuery("ForeignStudentView.existEmail").setParameter("email", email);
        String result = null;
        ForeignStudentView foreignStudentView = null;
        if(query.getResultList().size() > 0) {
            foreignStudentView = (ForeignStudentView)query.getResultList().get(0);
            result = foreignStudentView.getId();
        }
        return result;
    }

    public String existScoFSProgramLimit(String shyId,String scoYear,String campus,String levelId,String periodId){
        javax.persistence.Query query = entityManager.createNamedQuery("ScoFsProgramLimit.existLimit").setParameter("shyId", shyId).setParameter("scoYear", scoYear).setParameter("campus", campus).setParameter("levelId", levelId).setParameter("periodId", periodId);
        String result = null;
        ScoFsProgramLimit scoFsProgramLimit = null;
        if(query.getResultList().size() > 0) {
            scoFsProgramLimit = (ScoFsProgramLimit)query.getResultList().get(0);
            result = scoFsProgramLimit.getMessage();
        }
        return result;
    }

    @Transactional
    public void createForeignStudentAndSendMail(String foreignStudentId, String universityId, String host) throws Exception {

        ComArtificialPerson comArtificialPerson = findComArtificialPersonById(universityId);
        if(comArtificialPerson.getComJobList() != null && comArtificialPerson.getComJobList().size() > 0) { // Checking if a correspondant is linked to the university
            ComJob comJob = comArtificialPerson.getComJobList().get(0);
            ComMail mail = null;
            for(ComContactMail contactMail : comJob.getComNaturalPerson().getComContact().getComContactMailList()) { // Checking if the correspondant has a correct mail
                if(contactMail.getId().getAkdId().equalsIgnoreCase("S")) {
                    mail = contactMail.getComMail();
                }
            }
            if(mail != null) {
                ForeignStudent foreignStudent = findForeignStudentById(foreignStudentId);
                javax.persistence.Query query = entityManager.createNamedQuery("LoginOnDemand.findByEmail").setParameter("email", foreignStudent.getEmail());
                if(query.getResultList().size() == 0) { // No previous activation found with the email
                    LoginOnDemand lod = createLodForeignStudent(foreignStudentId); // creating the loginOnDemand for the student

                    if(lod != null) {
                        sendMailConfirmationForeignStudent(lod, comArtificialPerson.getComContact().getName(), host); // Sending the mail to the student
                        // Updating Student status
                        ForeignStudentStatus foreignStudentStatus = new ForeignStudentStatus();
                        ForeignStudentStatusPK foreignStudentStatusPK = new ForeignStudentStatusPK();
                        foreignStudentStatusPK.setFsId(foreignStudent.getId());
                        foreignStudentStatusPK.setCrtIdStatus("ATT");
                        foreignStudentStatusPK.setStatusDate(new Date());
                        foreignStudentStatus.setPk(foreignStudentStatusPK);

                        //foreignStudentStatus.setPk(new ForeignStudentStatus.PK());
                        //foreignStudentStatus.getPk().setFsId(foreignStudent.getId());
                        //foreignStudentStatus.getPk().setCrtIdStatus("ATT");
                        //foreignStudentStatus.getPk().setStatusDate(new Date());
                        foreignStudentStatus.setCrtCkaIdStatus("STATUS_FRN_SDT");
                        foreignStudent.getForeignStudentStatusList().add(foreignStudentStatus);
                        foreignStudent.setCntIdTemp(lod.getDId()); // Putting the id as temporary, when the student will create is account we can update is status
                        entityManager.merge(foreignStudent);
                        logger.info("student status updated (ATT) for : "+foreignStudentId);
                    }
                }else{
                    logger.error("Cannot create : "+foreignStudent.getEmail()+" Activation mail exists");
                    throw new Exception("foreignStudent.activationEmailExists");
                }
            }else{
                logger.error("Cannot create student for : "+universityId+" No correspondant mail found");
                throw new Exception("foreignStudent.noCorrespondantMail");
            }

        }else {
            logger.error("Cannot create student for : "+universityId+" No correspondant found");
            throw new Exception("foreignStudent.noCorrespondant");
        }
    }

    @Transactional
    public LoginOnDemand createLodForeignStudent(String foreignStudentId) {

        ForeignStudent foreignStudent = findForeignStudentById(foreignStudentId);
        LoginOnDemand lod = new LoginOnDemand();
        if(foreignStudent != null && foreignStudent.getForeignStudentStatusList().size() == 1 && foreignStudent.getForeignStudentStatusList().get(0).getPk().getCrtIdStatus().equalsIgnoreCase("I")) {

            lod.setDId(commonStudentAbroadService.generateId("com_genkey_contact"));
            lod.setDEmail(foreignStudent.getEmail().toLowerCase());
            lod.setDFirstName(foreignStudent.getFirstName());
            lod.setDLogin(foreignStudent.getEmail().toLowerCase());
            lod.setDName(foreignStudent.getLastName());
            lod.setDTitle(foreignStudent.getGender());

            //String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-@#&'(!?)$%?:;/.?,";
            String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@#&'(!?)$%?/?";
            Random rand = new Random();
            String password = "";
            for (int i=0; i<6; i++)
            {

                password += alphabet.charAt(rand.nextInt(alphabet.length()));
            }

            lod.setDPassword(password);

            // Generate the random number for validation
            Random random = new Random();
            long r1 = random.nextLong();
            long r2 = random.nextLong();

            String hash1 = Long.toHexString(r1);
            String hash2 = Long.toHexString(r2);
            String hash = hash1 + hash2;
            lod.setDActivation(hash);

            entityManager.persist(lod);
            logger.info("LoginOnDemand with id : "+lod.getDId()+" for foreignStudent : " + foreignStudentId +"created");

        }
        return lod;
    }

    private void sendMailConfirmationForeignStudent(LoginOnDemand lod, String universityName, String host) throws Exception{
        ResourceBundle mailBundle = ResourceBundle.getBundle("mail");
        ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

        String[] toAddresses = new String[1];
        toAddresses[0] = lod.getDEmail();

        StringBuffer mailText = new StringBuffer(messageBundle.getString("mail.logincreation.dear"));
        mailText.append(" ");
        mailText.append(lod.getDFirstName());
        mailText.append(",");
        mailText.append(messageBundle.getString("mail.logincreation.text1"));
        mailText.append(universityName);
        mailText.append(messageBundle.getString("mail.logincreation.text2"));
        mailText.append(" <a href='");
        mailText.append("http://");
        mailText.append(mailBundle.getString("mail.redirectHost"));
        mailText.append("/ons/foreignStudentActivation.htm?code=");
        mailText.append(lod.getDActivation());
        mailText.append("'>click here</a> ");
        mailText.append(messageBundle.getString("mail.logincreation.text3"));

        CommonEdhecMailSender.commonEdhecMailSender(mailBundle.getString("mail.server"),mailBundle.getString("mail.server.port"),mailBundle.getString("mail.server.login"),mailBundle.getString("mail.server.pwd"),mailBundle.getString("mail.from"),toAddresses,null,messageBundle.getString("mail.logincreation.subject"),mailText.toString());
        logger.info("sendMailConfirmationForeignStudent : "+lod.getDEmail()+" sent");

    }

    @Transactional
    public String createForeignStudent(String idUniversity, String program, String year, ForeignStudentCommand command ) {

        ForeignStudent foreignStudent = null;
        try{
            if(command.getForeignStudentId() == null || command.getForeignStudentId().trim().length() == 0 ) {
                String foreignStudentNewId = commonStudentAbroadService.generateId("SCO_GENKEY_FOREIGN_STUDENT");

                foreignStudent = new ForeignStudent();
                foreignStudent.setId(foreignStudentNewId);
                foreignStudent.setIdUniversity(idUniversity);
            }else{
                foreignStudent = findForeignStudentById(command.getForeignStudentId());
            }
            foreignStudent.setShyId(program);
            foreignStudent.setScoYear(new BigDecimal(year));
            foreignStudent.setLastName(command.getStudentLastName().trim());
            foreignStudent.setFirstName(command.getStudentFirstName().trim());
            foreignStudent.setGender(command.getStudentTitle());
            foreignStudent.setBirthdate(command.getBirthDate());
            if (command.getNationality() != null) {
                foreignStudent.setNationality(command.getNationality());
            }
            foreignStudent.setEmail(command.getStudentEmail().trim());
            foreignStudent.setHelp(command.getHelp());
            if (command.getCurrentyear() != null){
                foreignStudent.setCurrentyear(command.getCurrentyear().trim());
            }
            if (command.getCurrentyearoutof() != null){
                foreignStudent.setCurrentyearoutof(command.getCurrentyearoutof().trim());
            }

            //Recherche de la periode, campus, levelCode et dbleDegree du niveau selectionne
            foreignStudent.setModuleCode(command.getStudentLevel());
            BookNivChoixIncoming bookNivChoixIncoming = findBookNivChoixIncomingbyCode(command.getStudentLevel(), year);
            String periode = bookNivChoixIncoming.getPeriodeCode();
            String campus = bookNivChoixIncoming.getCampus();
            String levelCode = bookNivChoixIncoming.getLevelCode();
            String dbleDegree = bookNivChoixIncoming.getDbleDegree();

            foreignStudent.setStudylevel(levelCode);
            foreignStudent.setCampus(campus);
            foreignStudent.setDoubleDiploma(dbleDegree);

            foreignStudent.setPeriodeCode(periode);
		/*
		foreignStudent.setFall("");
		foreignStudent.setWinter("");
		foreignStudent.setSpring("");

		if (periode.contentEquals("S0")) {
			foreignStudent.setFall("1");
			foreignStudent.setWinter("1");
			foreignStudent.setSpring("1");
		}

		if (periode.contentEquals("S1")) {
			foreignStudent.setFall("1");
			foreignStudent.setWinter("1");
			foreignStudent.setSpring("0");
		}

		if (periode.contentEquals("S2")) {
			foreignStudent.setFall("0");
			foreignStudent.setWinter("1");
			foreignStudent.setSpring("1");
		}*/

            if(command.getForeignStudentId() == null || command.getForeignStudentId().trim().length() == 0 ) {
                ForeignStudentStatus foreignStudentStatus = new ForeignStudentStatus();
                ForeignStudentStatusPK foreignStudentStatusPK = new ForeignStudentStatusPK();
                foreignStudentStatusPK.setFsId(foreignStudent.getId());
                foreignStudentStatusPK.setCrtIdStatus("I");
                foreignStudentStatusPK.setStatusDate(new Date());
                foreignStudentStatus.setPk(foreignStudentStatusPK);
                //foreignStudentStatus.setPk(new ForeignStudentStatus.PK());
                //foreignStudentStatus.getPk().setFsId(foreignStudent.getId());
                //foreignStudentStatus.getPk().setCrtIdStatus("I");
                //foreignStudentStatus.getPk().setStatusDate(new Date());
                foreignStudentStatus.setCrtCkaIdStatus("STATUS_FRN_SDT");
                foreignStudent.setForeignStudentStatusList(new ArrayList<ForeignStudentStatus>());
                foreignStudent.getForeignStudentStatusList().add(foreignStudentStatus);
                entityManager.persist(foreignStudent);
                entityManager.flush();
                logger.info(foreignStudent.getId()+" created");
            }else {
                entityManager.merge(foreignStudent);
                //entityManager.flush();
                logger.info(foreignStudent.getId()+" updated");
            }

            updateForeignUniversityCapacity(idUniversity, program, year);

            return "OK";
        }catch (Exception e){
            logger.info("Error createForeignStudent : " + e);
            return "NOK";
        }
    }

    @Transactional
    public void deleteForeignStudent(String foreignStudentId) {

        ForeignStudent fs = findForeignStudentById(foreignStudentId);
        String idUniversity = fs.getUniversity().getIdUniversity();
        String program = fs.getUniversity().getShyId();
        String year = fs.getUniversity().getScoYear() + "";
        entityManager.remove(fs);
        entityManager.flush();

        updateForeignUniversityCapacity(idUniversity, program, year);

        logger.info(foreignStudentId+" deleted");
    }
    @Transactional
    public void registerForeignStudent(String foreignStudentId){
        // Updating Student status
        ForeignStudentStatus foreignStudentStatus = new ForeignStudentStatus();
        ForeignStudentStatusPK foreignStudentStatusPK = new ForeignStudentStatusPK();
        foreignStudentStatusPK.setFsId(foreignStudentId);
        foreignStudentStatusPK.setCrtIdStatus("I");
        foreignStudentStatusPK.setStatusDate(new Date());
        foreignStudentStatus.setPk(foreignStudentStatusPK);
        foreignStudentStatus.setCrtCkaIdStatus("STATUS_FRN_SDT");
        entityManager.merge(foreignStudentStatus);
        logger.info("student status updated (I) for : "+foreignStudentId);
    }

    @Transactional
    public void confirmForeignStudent(String foreignStudentId){
        // Updating Student status
        ForeignStudentStatus foreignStudentStatus = new ForeignStudentStatus();
        ForeignStudentStatusPK foreignStudentStatusPK = new ForeignStudentStatusPK();
        foreignStudentStatusPK.setFsId(foreignStudentId);
        foreignStudentStatusPK.setCrtIdStatus("CC");
        foreignStudentStatusPK.setStatusDate(new Date());
        foreignStudentStatus.setPk(foreignStudentStatusPK);
        foreignStudentStatus.setCrtCkaIdStatus("STATUS_FRN_SDT");
        entityManager.merge(foreignStudentStatus);
        logger.info("student status updated (CC) for : "+foreignStudentId);
    }

    @Transactional
    public void cancelStudent(String foreignStudentId){
        // Updating Student status
        ForeignStudentStatus foreignStudentStatus = new ForeignStudentStatus();
        ForeignStudentStatusPK foreignStudentStatusPK = new ForeignStudentStatusPK();
        foreignStudentStatusPK.setFsId(foreignStudentId);
        foreignStudentStatusPK.setCrtIdStatus("A");
        foreignStudentStatusPK.setStatusDate(new Date());
        foreignStudentStatus.setPk(foreignStudentStatusPK);
        foreignStudentStatus.setCrtCkaIdStatus("STATUS_FRN_SDT");
        entityManager.merge(foreignStudentStatus);
        logger.info("student status canceled (A) for : "+foreignStudentId);
    }
}

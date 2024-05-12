package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Programme;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.dtos.ProgrammeDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

public interface ProgrammeManger {
    Page<ProgrammeDTO> listProgrammes(String kw, int size, int  page);
    ProgrammeDTO AddProgramme(MultipartFile attachment, ProgrammeDTO programmeDTO,String coachemail,Collection<ClientDTO> clients) throws IOException;

    ProgrammeDTO updateProgramme(Long id, ProgrammeDTO programmeDTO);

    boolean DeleteProgramme(Long id);

    Page<ProgrammeDTO> listProgrammesCoach(String kw, int size, int page, String coachemail);
}

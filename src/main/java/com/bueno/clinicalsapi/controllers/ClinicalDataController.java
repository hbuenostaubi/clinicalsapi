package com.bueno.clinicalsapi.controllers;

import com.bueno.clinicalsapi.dto.ClinicalDataRequest;
import com.bueno.clinicalsapi.model.ClinicalData;
import com.bueno.clinicalsapi.model.Patient;
import com.bueno.clinicalsapi.repos.ClinicalDataRepository;
import com.bueno.clinicalsapi.repos.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalDataController {

    private ClinicalDataRepository clinicalDataRepository;
    private PatientRepository patientRepository;

    @Autowired
    ClinicalDataController(ClinicalDataRepository clinicalDataRepository, PatientRepository patientRepository){
        this.clinicalDataRepository = clinicalDataRepository;
        this.patientRepository = patientRepository;
    }


    @RequestMapping(value = "/clinicals", method = RequestMethod.POST)
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request){
    Patient patient = patientRepository.findById(request.getPatientId()).get();
    ClinicalData clinicalData = new ClinicalData();
    clinicalData.setComponentName(request.getComponentName());
    clinicalData.setComponentValue(request.getComponentValue());
    clinicalData.setPatient(patient);

    return clinicalDataRepository.save(clinicalData);
    }

}

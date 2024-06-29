package ru.kurochkin.temperatureSensor.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kurochkin.temperatureSensor.dto.MeasurementsDTO;
import ru.kurochkin.temperatureSensor.model.Measurements;
import ru.kurochkin.temperatureSensor.service.MeasurementsService;
import ru.kurochkin.temperatureSensor.util.MeasurementsNotCreatedException;
import ru.kurochkin.temperatureSensor.util.MeasurementsValidator;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final MeasurementsValidator measurementsValidator;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, MeasurementsValidator measurementsValidator) {
        this.measurementsService = measurementsService;
        this.measurementsValidator = measurementsValidator;
    }

    @GetMapping
    public List<Measurements> getMeasurements(){
        return measurementsService.findAll();
    }

    @GetMapping("/rainyDaysCount")
    public String getRainyDaysCount(){
        Integer count = 0;
        List<Measurements> list =  measurementsService.findAll();

        for (Measurements l : list){
            if (l.isRaining() == true) {
                count++;
            }
        }

        return "Number of rainy days: " + String.valueOf(count);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid MeasurementsDTO measurementsDTO,
                                                   BindingResult bindingResult){
        Measurements measurementToAdd = convertToMeasurment(measurementsDTO);

        measurementsValidator.validate(measurementToAdd, bindingResult);

        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError e: errors) {
                stringBuilder.append(e.getField())
                        .append(" - ").append(e.getDefaultMessage())
                        .append(";");
            }

            throw new MeasurementsNotCreatedException(stringBuilder.toString());
        }
        measurementsService.save(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public Measurements convertToMeasurment(MeasurementsDTO measurementsDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(measurementsDTO, Measurements.class);
    }
}

package ru.dev.calcapp;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class CalcController {
    @Autowired
    private OperationModelRepository operationModelRepository;

    //Сложение
    @GetMapping("/add")
    public String add(Model model, @RequestParam double... numbers){
        double res = 0;
        for ( double num: numbers){
            res += num;
        }
        model.addAttribute("result", res);
        OperationModel operationModel = new OperationModel();
        operationModel.setOperationName("Сложение: " + Arrays.toString(numbers) + " Результат:" + res);
        operationModelRepository.save(operationModel);
        return "index";
    }
    //Вычитание
    @GetMapping("/sub")
    public String subtract(Model model, @RequestParam double... numbers){
        double res = numbers[0];
        for (int i = 1; i <numbers.length;i++){
            res -= numbers[i];
        }
        model.addAttribute("result", res);
        OperationModel operationModel = new OperationModel();
        operationModel.setOperationName("Вычитание: " + Arrays.toString(numbers) + " Результат:" + res);
        operationModelRepository.save(operationModel);
        return "index";
    }
    //Умножение
    @GetMapping("/dupe")
    public String multiply(Model model, @RequestParam double... numbers) {
        double res = 1;
        for (double num : numbers) {
            res *= num;
        }
        model.addAttribute("result", res);
        OperationModel operationModel = new OperationModel();
        operationModel.setOperationName("Умножение: " + Arrays.toString(numbers) + " Результат:" + res);
        operationModelRepository.save(operationModel);
        return "index";
    }
    //Комбо операция , сложение первых двух и умножение на 3е число
    // Достаточно чёткое задание , всего 3 переменные )))))
    @GetMapping("/combo")
    public String combo(Model model, @RequestParam String comboNumbers) {
        String[] numbersArray = comboNumbers.split(",");
        if (numbersArray.length != 3) {
            throw new IllegalArgumentException("Нужно ввести три числа через запятую");
        }

        // Преобразуем строки в числа
        double num1 = Double.parseDouble(numbersArray[0].trim());
        double num2 = Double.parseDouble(numbersArray[1].trim());
        double num3 = Double.parseDouble(numbersArray[2].trim());
        double result = (num1 * num2) + num3;
        model.addAttribute("result", result);
        OperationModel operationModel = new OperationModel();
        operationModel.setOperationName("Комбо: " + comboNumbers + " Результат:" + result);
        operationModelRepository.save(operationModel);
        return "index";
    }
// Вывод истории операций
    @GetMapping("/")
    public ModelAndView showHistory() {
        List<OperationModel> history = operationModelRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("history", history);
        return modelAndView;
    }


}

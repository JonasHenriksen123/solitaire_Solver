package group_01.solverapi;

import group_01.solverapi.control.Controller;
import group_01.solverapi.picrecaccess.CardPlacementDAO;
import group_01.solverapi.picrecaccess.ICardPlacementDAO;
import group_01.solverapi.picrecaccess.TestCardPlacementDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SolverapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SolverapiApplication.class, args);
    }
}

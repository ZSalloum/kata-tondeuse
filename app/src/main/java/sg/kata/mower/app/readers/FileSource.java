package sg.kata.mower.app.readers;

import sg.kata.mower.app.Main;
import sg.kata.mower.core.MowerException;
import sg.kata.mower.core.readers.IInputSourceReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileSource implements IInputSourceReader {
    private static final Logger logger = LogManager.getLogger(FileSource.class);

    private static final int HEADER_STEP = -1;
    private static final int MOWER_STEP = 0;
    private static final int COMMAND_STEP = 1;

    private int currentStep = HEADER_STEP;
    private String filepath;
    private Scanner scanner;
    public  FileSource(String filepath){
        this.filepath = filepath;
    }

    public void open() throws FileNotFoundException {
        logger.info("Opening file : {}", filepath);
        scanner = new Scanner(new FileReader(filepath));
    }

    public void close(){
        scanner.close();
    }

    @Override
    public String readHeader() {
        logger.info("Reading header");
        return readNextStepLine(HEADER_STEP);
    }

    @Override
    public String readMower() {
        logger.info("Reading mower info");
        return readNextStepLine(MOWER_STEP);
    }

    @Override
    public String readMowerCommands() {
        logger.info("Reading header command");
        return readNextStepLine(COMMAND_STEP);
    }

    private String readNextStepLine(int step){
        if (currentStep == step) {
            nextStep();
            return readNextLine();
        }
        throw new MowerException(getErrorMessage());
    }

    private String readNextLine(){
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    private String getErrorMessage(){
        switch (currentStep){
            case -1: return "Expecting Header to be read";
            case 0: return "Expecting Mower to be read";
            case 1: return "Expecting Commands to be read";
        }
        return "Unknown error";
    }

    private void nextStep(){
        currentStep = (currentStep + 1) % 2;
    }
}

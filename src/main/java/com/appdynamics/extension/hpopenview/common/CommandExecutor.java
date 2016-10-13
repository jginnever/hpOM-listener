package com.appdynamics.extension.hpopenview.common;


//import com.appdynamics.extension.hpopenview.Configuration;
import com.appdynamics.extension.hpopenview.api.Alert;
import org.apache.commons.exec.CommandLine;
import org.apache.log4j.Logger;

import java.io.IOException;

public class CommandExecutor {

    private CommandBuilder commandBuilder = new CommandBuilder();

    private static Logger logger = Logger.getLogger(CommandExecutor.class);


    public boolean execute(String commandPath, Alert alert) throws CommandExecutorException {
        CommandLine command = null;
        command = commandBuilder.buildCommand(commandPath, alert);
        StringBuilder commBuilder = new StringBuilder();
        for(String comm : command.toStrings()){
            commBuilder.append(comm + " ");
        }
        logger.info("Command to be executed is :: " + commBuilder.toString());

        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            p = rt.exec(command.toStrings());
            StreamGobbler errorGobbler = new
                    StreamGobbler(p.getErrorStream(), "ERROR");

            // any output?
            StreamGobbler outputGobbler = new
                    StreamGobbler(p.getInputStream(), "OUTPUT");

            // kick them off
            errorGobbler.start();
            outputGobbler.start();
            int exitVal = p.waitFor();
            if(exitVal != 0){
                logger.error("Unable to generate alert. ExitValue = " + exitVal);
                return false;
            }
        } catch (IOException e) {
            //logger.error("Error in executing the command " + e);
            throw new CommandExecutorException("Execution failed with message "+ e.getMessage(), e);
        } catch (InterruptedException e) {
            //logger.error("Execution of command got interrupted " + e);
            throw new CommandExecutorException("Execution of command got interrupted  "+ e.getMessage(), e);
        }
        return true;
    }

    /*private void logDebugProcessExecution(Process p){

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        // read the output from the command
        logger.debug("Output from the command:\n");
        String s;
        try {
            while ((s = stdInput.readLine()) != null) {
                logger.info(s);
            }
        } catch (IOException e) {
            logger.error("Error in accessing the stdInput stream " + e);
        }
        // read any errors from the attempted command
        logger.info("Error stream from the command:\n");
        try {
            while ((s = stdError.readLine()) != null) {
                logger.info(s);
            }
        } catch (IOException e) {
            logger.error("Error in accessing the error stream " + e);
        }
    }*/


}

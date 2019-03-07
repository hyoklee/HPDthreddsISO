package thredds.service.ncdismember;

// Import classes for ncdismember command line interface. 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;
import thredds.servlet.ServletUtil;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ncdismember")
public class NCDismemberServiceController {

   @RequestMapping("/**")
   public ResponseEntity<String> handleRequest(HttpServletRequest request, HttpServletResponse response){

       HttpHeaders responseHeaders = new HttpHeaders();
       responseHeaders.setContentType(MediaType.TEXT_PLAIN);

       String outStr = "";
       try {
           // Execute command and capture output.
           Runtime runtime = Runtime.getRuntime();
           Process process = null;

           System.out.println( "processing command..." );
           String path = ServletUtil.getRequestPath(request);
           path = path.substring(12);
           System.out.println( "path=" + path);
           String command[] = {"/hdfdap/hdfeos/bin/ncdismember", path};
           process = runtime.exec(command);
			
           // Get stdout stream.
           BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));

           // Read and save output in string.
           System.out.println( "Reading stdout..." );
           String line = null;
           while ((line = stdout.readLine()) != null) {
               outStr += line + "\n";
           }
           // Get stderr stream.
           BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
           while ((line = stderr.readLine()) != null) {
               outStr += line + "\n";
           }

           // check for process done
           //System.out.println( "Checking exit code..." );
           int exitCode = process.waitFor();
           if (exitCode != 0) {
               outStr += "process failed: "+exitCode;
           }
                                    
       }
       catch (Exception e) {
           outStr += "Error executing command: " + e.toString();
       }
       System.out.println(outStr);
       return new ResponseEntity<String>(outStr, responseHeaders, HttpStatus.OK);

   }
    
}

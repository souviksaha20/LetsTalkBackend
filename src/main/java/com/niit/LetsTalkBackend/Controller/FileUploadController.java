package com.niit.LetsTalkBackend.Controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.LetsTalkBackend.Dao.FileUploadDAO;
import com.niit.LetsTalkBackend.Model.UploadFile;
import com.niit.LetsTalkBackend.Model.User;

@RestController
public class FileUploadController {
	@Autowired
	private FileUploadDAO fileUploadDao;

	
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String handleFileUpload(HttpServletRequest request,
    		HttpSession session,
            @RequestParam CommonsMultipartFile fileUpload) throws Exception {
         User user=(User)session.getAttribute("user");
         if(user==null)
        	 throw new RuntimeException("Not logged in");
         System.out.println("USER is " + user.getUsername());
         
         if (fileUpload != null ) {
             CommonsMultipartFile aFile = fileUpload;
            
                System.out.println("Saving file: " + aFile.getOriginalFilename());
                
                UploadFile uploadFile = new UploadFile();
                uploadFile.setFileName(aFile.getOriginalFilename());
                uploadFile.setData(aFile.getBytes());//image 
                uploadFile.setUsername(user.getUsername());//login details
                fileUploadDao.save(uploadFile);
                //select * from proj2_profie_pics where username='smith'
                UploadFile getUploadFile=fileUploadDao.getFile(user.getUsername());
            	String name=getUploadFile.getFileName();
            	System.out.println(getUploadFile.getData());
            	byte[] imagefiles=getUploadFile.getData();  //image
            	try{
            		
            		String path="C:/Users/CHAITHANYA/sam/project-2/LetsTalkBackend/src/main/resources/images/"+user.getUsername();
            		File file=new File(path);
            		
            		FileOutputStream fos = new FileOutputStream(file);
            		fos.write(imagefiles);
            		fos.close();
            		}catch(Exception e){
            		e.printStackTrace();
            		}
             }
                
 
        return "Successfully uploaded the Profile Picture";
    }

	@RequestMapping(value="/getFile",method=RequestMethod.GET)
	public ResponseEntity<?> getFile(HttpSession session){
   User user=(User)session.getAttribute("user");
	UploadFile uploadFile=fileUploadDao.getFile(user.getUsername());
	String name=uploadFile.getFileName();
	System.out.println(uploadFile.getData());
	byte[] imagefiles=uploadFile.getData();

	return new ResponseEntity<byte[]>(imagefiles,HttpStatus.OK);
}

}

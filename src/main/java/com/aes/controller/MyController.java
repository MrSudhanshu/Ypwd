package com.aes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aes.service.MyService;

@RestController
public class MyController {

	@Autowired
	MyService myService;
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/decrypt", method = RequestMethod.POST)
    public String decrypt(@RequestBody String encryptedString) {
        return myService.decrypt(encryptedString);
    }
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/encrypt", method = RequestMethod.POST)
    public String encrypt(@RequestBody String encryptedString) {
        return myService.encrypt(encryptedString);
    }
}

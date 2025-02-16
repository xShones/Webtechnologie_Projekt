package com.uni.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uni.project.entity.Transaction;
import com.uni.project.service.TransactionServiceImp;

@Controller
public class WebController {

	private final TransactionServiceImp transactionService;

	public WebController(TransactionServiceImp transactionService){
		this.transactionService = transactionService;
	}

    @GetMapping("/")
	public String startpage() {
		return "startpage";
	}

	@GetMapping("/overview")
	public String overview(Model model){

		List<Transaction> transactionList = transactionService.getTransactionsByUserId((long)1);
		model.addAttribute("transactionList", transactionList);

		return "overview";
	}
}

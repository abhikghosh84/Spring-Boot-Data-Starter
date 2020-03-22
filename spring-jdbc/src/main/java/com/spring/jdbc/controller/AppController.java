package com.spring.jdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jdbc.dao.SalesDao;
import com.spring.jdbc.model.Sale;

@Controller
public class AppController {
	
	@Autowired
	private SalesDao dao;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		
		List<Sale> sales = dao.list();
		model.addAttribute("sales", sales);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewForm(Model model) {
		
		Sale sale = new Sale();
		model.addAttribute("sale", sale);
		return "new_form";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@ModelAttribute("sale") Sale sale) {
		dao.save(sale);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_form");
		mav.addObject("sale", dao.get(id));
		return mav;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute("sale") Sale sale) {
		dao.update(sale);
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable(name = "id") int id) {
		dao.delete(id);
		return "redirect:/";
	}
	
}

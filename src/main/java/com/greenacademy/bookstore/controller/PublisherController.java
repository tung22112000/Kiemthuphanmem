package com.greenacademy.bookstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.bookstore.entities.Publisher;
import com.greenacademy.bookstore.repository.PublisherRepository;
import com.greenacademy.bookstore.service.impl.ImageServiceImpl;
import com.greenacademy.bookstore.service.impl.PublisherServiceImpl;

@Controller
@RequestMapping(value = "/admin/publisher")
public class PublisherController {
	
	@Autowired
	private PublisherServiceImpl publisherService;
	@Autowired
	private ImageServiceImpl imageService;
	@Autowired
	private PublisherRepository publisherRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView getPublisher(ModelAndView model,@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "5") Integer size) {
		model.setViewName("publisher-publishers");
		Page<Publisher> publishers= this.publisherService.getAllWithPagination(page,size,"id");
		model.addObject("publishers", publishers);
		model.addObject("currentPage",page);
		return model;
		
	}
	@RequestMapping(value="/save", method=  RequestMethod.POST)
	public String savePublisher(@ModelAttribute("publisher") Publisher publisher,Model model, HttpServletRequest request) {
		String uploadRootPath = request.getServletContext().getRealPath("uploads");


		String imageName = imageService.uploadFile(uploadRootPath,publisher.getImageFile());
		String error="";
		String getPublisherName= publisher.getPublisherName();
		Publisher publisherFindByPublisherName= publisherRepository.findByPublisherName(getPublisherName);
		
		if(publisher.getId() == null) {
			
			if(imageName!=null  && imageName.isEmpty()==false) {
				publisher.setLogo(imageName);
			}else {
				publisher.setLogo("Logo.png");;
			}
			if(publisherFindByPublisherName!=null) {
				error ="Tên nhà xuất bản đã tồn tại!";
				model.addAttribute("errorMessage",error);
				model.addAttribute("mode","ADD");
				return "publisher-publisherUpdate";
			}
			String errorMes = publisherService.add(publisher);
			if(errorMes!=null) {
			model.addAttribute("errorMessage",errorMes);
			model.addAttribute("mode","ADD");
			return "publisher-publisherUpdate";
			}
		} else {
			if(imageName!=null  && imageName.isEmpty()==false) {
				publisher.setLogo(imageName);
				}
			else {
				publisher.setLogo("Logo.png");;
			}
			String errorMessage = publisherService.update(publisher);
			if(errorMessage!=null){
				model.addAttribute("errorMessage",errorMessage);
				model.addAttribute("mode","EDIT");
				return "publisher-publisherUpdate";
			}
			return"publisher-publisherDetail";
		}
		return "redirect:/admin/publisher/";

	}

	@RequestMapping(value="/getPublisher/{id}", method=  RequestMethod.GET)
	public ModelAndView getProduct(@PathVariable("id")Long id, @RequestParam("mode") String mode , ModelAndView model) {

		if (mode.equals("EDIT") ) {
			model.setViewName("publisher-publisherUpdate");
		} else {
			model.setViewName("publisher-publisherDetail");
		}

		Publisher publisher = publisherService.getPublisher(id);

		model.addObject("publisher",publisher);
		model.addObject("mode",mode);
		return model;
	}

	@RequestMapping(value="/addPublisher", method=  RequestMethod.GET)
	public ModelAndView addPublisher(ModelAndView model) {
		model.setViewName("publisher-publisherUpdate");
		model.addObject("publisher",new Publisher());
		model.addObject("mode","ADD");
		return model;
	}

	@RequestMapping(value="/deletePublisher/{id}", method=  RequestMethod.GET,headers = "Accept=application/json")
	public String deletePublisher(@PathVariable Long id) {
		publisherService.delete(id);
		return "redirect:/admin/publisher/";
	}
	@RequestMapping(value="/search", method=  RequestMethod.POST)
	public ModelAndView search(@RequestParam String search, ModelAndView model) {
		boolean kq = false;
		List<Publisher> publishers = this.publisherService.getFindPublisher(search);
		 if (publishers.size() > 0)
			   {
				  model.addObject("publishers", publishers);
				  kq = true;
		       }
		model.addObject("ketqua",kq);
		model.addObject("size", publishers.size());
		model.addObject("search",search);
		model.setViewName("publisher-searchPublisher");
		return model;
	}
}

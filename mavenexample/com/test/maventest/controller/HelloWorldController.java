package com.test.maventest.controller;
@Controller
@RequestMapping("/helloWorld")
public class HelloWorldController {
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		model.String hello(ModelMap model) {
			model.addAttribute("msg", "Hello World!") //데이터
			return "hello world"; //리턴되는 jsp명
		}
		
		@RequestMapping(value = "/displayMessage/{msg}"), method = RequestMethod.GET)
		public String displayMessage(@PathVariable String msg, ModelMap model) {
			model.addAttribute("msg", msg); //데이터
			return "helloWorld"; //리턴 파일명
		}
}

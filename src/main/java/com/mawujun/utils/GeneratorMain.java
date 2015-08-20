package com.mawujun.utils;

import java.io.IOException;

import com.mawujun.generator.GeneratorService;
import com.mawujun.provice.Provice;

import freemarker.template.TemplateException;


public class GeneratorMain {
	static GeneratorService generatorService=new GeneratorService();
	public static void main(String[] args) throws ClassNotFoundException, IOException, TemplateException {
		//generatorService.setExtenConfig(new ExtenConfig());
		// TODO Auto-generated method stub
		generatorService.generatorAllFile(Provice.class);
	}

}

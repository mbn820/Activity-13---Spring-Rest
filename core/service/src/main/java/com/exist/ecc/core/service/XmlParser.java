package com.exist.ecc.core.service;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import com.exist.ecc.core.model.dto.PersonDto;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlParser {

	public PersonDto convertToPersonDto(File xmlFile) throws IOException {
		String xmlString = FileUtils.readFileToString(xmlFile, "UTF-8");
		return new XmlMapper().readValue(xmlString, PersonDto.class);
	}

}

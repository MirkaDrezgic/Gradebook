package com.iktpreobuka.gradebook.services;



public class Snippet {
	
	
	
//	/************************************************************************
//	 * PUT gradebook/class/{idClass}/sections
//	 *
//	 * @body sectionList //dodavanje vise odeljenja razredu
//	 * 
//	 ************************************************************************/
//
//	@RequestMapping(method = RequestMethod.PUT, value = "/{idClass}/sections")
//	public ResponseEntity<?> addListSection(@PathVariable Integer idClass, @RequestBody StringsParamDTO sectionList) {
//		try {
//			if (sectionList.getParams().size() > 0) {
//				for (String sectionName : sectionList.getParams()) {
//					ClassEntity classDB = classRepository.findOne(idClass);
//					SectionEntity sectionDB = new SectionEntity();
//					if (sectionService.existsSectionForClass(idClass, strToSectionType.convert(sectionName))) {
//						// ako vec postoji ovo odeljenje greska
//						return new ResponseEntity<RESTError>(
//								new RESTError(21, "Section with name " + sectionName + " exists in database"),
//								HttpStatus.BAD_REQUEST);
//					} else if (classDB == null) {
//						// ako ne postoji razred greska
//						return new ResponseEntity<RESTError>(
//								new RESTError(1, "Class with id " + idClass + " not found"), HttpStatus.NOT_FOUND);
//					} else {
//						// inace napravi novo odeljenje
//						sectionDB.setSectionType(strToSectionType.convert(sectionName));
//						sectionDB.setClassEntity(classDB);
//						classRepository.save(classDB);
//						sectionRepository.save(sectionDB);
//						return new ResponseEntity<SectionDTO>(sectionToDTO.convert(sectionDB), HttpStatus.CREATED);
//					}
//				}
//			}
//		} catch (Exception e) {
//			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
//					HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		return null;
//
//	}
//	
//	/************************************************************************
//	 *						 POST gradebook/parent
//	 ************************************************************************/
//	
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<?> saveNewParent(@RequestBody ParentEntity parent) {
//		parentRepository.save(parent);
//		return new ResponseEntity<ParentEntity>(parent, HttpStatus.CREATED);
//	}
//	
//	/************************************************************************
//	 *						PUT gradebook/parent/{id}
//	 ************************************************************************/
//	
//	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
//	public ResponseEntity<?> updateParent(@PathVariable Integer id, @RequestBody ParentEntity parent) {
//		ParentEntity parentDB = parentRepository.findOne(id);
//		if (parentDB != null) {
//			if (parent.getFirstName() != null) {
//				parentDB.setFirstName(parent.getFirstName());
//			}
//			if (parent.getLastName() != null) {
//				parentDB.setLastName(parent.getLastName());
//			}
//			if (parent.getEmail() != null) {
//				parentDB.setEmail(parent.getEmail());
//			}
//			if (parent.getPassword() != null) {
//				parentDB.setPassword(parent.getPassword());
//			}
//			if (parent.getJmbg() != null) {
//				parentDB.setJmbg(parent.getJmbg());
//			}
//			if (parent.getAddress()!=null) {
//				parentDB.setAddress(parent.getAddress());
//			}
//			parentRepository.save(parentDB);
//			return new ResponseEntity<ParentEntity>(parentDB, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<RESTError>(new RESTError(1, "Parent with id " + id + "not found"),
//					HttpStatus.NOT_FOUND);
//		}
//
//	}
	
	
//	/************************************************************************
//	 * POST gradebook/section
//	 * 
//	 * @param listLabel
//	 ************************************************************************/
//
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<?> saveNewSection(@RequestParam List<String> listLabel) {
//		// List<SectionEntity> sectionsDB = new ArrayList<SectionEntity>();
//		// for (String s : listLabel) {
//		// SectionEntity sectionDB=new SectionEntity();
//		// sectionDB.setSectionType(listLabel.valueOf);
//		// }
//		// for (ClassType cl : ClassType.values()) {
//		// ClassEntity classDB = new ClassEntity();
//		// classDB.setClassType(cl);
//		// classDB.setYear(year);
//		// classRepository.save(classDB);
//		// classList.add(classDB);
//		// }
//		//
//		// return new
//		// ResponseEntity<List<ClassDTO>>(classToDTO.convert(classList),
//		// HttpStatus.CREATED);
//		return null;
//
//	}

}


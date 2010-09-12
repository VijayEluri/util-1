package org.macrobug.util.note.processor;

import java.lang.annotation.*;
import javax.annotation.processing.*;
import javax.lang.model.element.Element;

public abstract class SpecificAnnotationTypeProcessor {
	private Class<? extends Annotation> classes;
	public SpecificAnnotationTypeProcessor(Class<? extends Annotation> classes){
		this.classes=classes;
	}
	public Class<? extends Annotation> getAnnotattionClass(){
		return this.classes;
	}
	public abstract void process(Element e, RoundEnvironment env, ProcessingEnvironment process);
}

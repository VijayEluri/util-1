package org.macrobug.util.note.processor;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.tools.Diagnostic.Kind;

import org.macrobug.util.note.Nn;

public class NnProcessor extends SpecificAnnotationTypeProcessor {

	public NnProcessor(){
		super(Nn.class);
	}
	@Override
	public void process(Element e, RoundEnvironment env,
			ProcessingEnvironment process) {
		if(!(e instanceof VariableElement)){
			throw new IllegalArgumentException("Solo a parametri");
		}
		VariableElement var=(VariableElement) e;
		TypeKind type=var.asType().getKind();
		if(type.isPrimitive()){
			process.getMessager().printMessage(Kind.ERROR, "Parametro non nullable",e);
		}
		
	}

}

package org.macrobug.util.note.processor;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.type.*;
import javax.tools.Diagnostic.Kind;

public class RetNnProcessor extends SpecificAnnotationTypeProcessor {

	public RetNnProcessor() {
		super(org.macrobug.util.note.RetNn.class);
	}

	@Override
	public void process(Element e, RoundEnvironment env,
			ProcessingEnvironment process) {
		if(!(e instanceof ExecutableElement)){
			throw new IllegalArgumentException("Annotazione apposta ad un elemento non eseguibile");
		}
		ExecutableElement ee=(ExecutableElement) e;
		TypeMirror tm=ee.getReturnType();
		if(tm.getKind().equals(TypeKind.VOID)){
			process.getMessager().printMessage(Kind.ERROR, "Annotazione apposta ad un metodo che restituisce void",e);
		}else if(tm.getKind().isPrimitive()){
			process.getMessager().printMessage(Kind.ERROR, "Annotazione apposta ad un metodo che restituisce primitivi", e);
		}
	}

}

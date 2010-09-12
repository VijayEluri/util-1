package org.macrobug.util.note.processor;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.tools.Diagnostic.Kind;

import org.macrobug.util.note.Range;

public class RangeProcessor extends SpecificAnnotationTypeProcessor {

	public RangeProcessor() {
		super(Range.class);
	}

	@Override
	public void process(Element e, RoundEnvironment env,
			ProcessingEnvironment process) {
		if(!(e instanceof VariableElement)){
			throw new IllegalArgumentException("Solo a parametri");
		}
		VariableElement var=(VariableElement) e;
		TypeKind type=var.asType().getKind();
		if(!type.equals(TypeKind.INT) && type.equals(TypeKind.LONG)){
			process.getMessager().printMessage(Kind.ERROR, "Parametro usabile solo su interi e long",e);
			return;
		}
		Range range=(Range) e.getAnnotation(this.getAnnotattionClass());
		if(range.max()<range.min()){
			process.getMessager().printMessage(Kind.ERROR, "massimi e minimi non in ordine in "+e.getSimpleName(),e);
		}
	}

}

package org.macrobug.util.note.processor;

import static javax.lang.model.SourceVersion.RELEASE_6;

import java.lang.annotation.Annotation;
import java.util.*;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes({"org.macrobug.util.note.*"})
@SupportedSourceVersion(RELEASE_6)
public class NoteProcessor extends AbstractProcessor {
	private List<SpecificAnnotationTypeProcessor> processor;
	public NoteProcessor() {
		processor=new LinkedList<SpecificAnnotationTypeProcessor>();
		processor.add(new NnProcessor());
		processor.add(new RangeProcessor());
		processor.add(new RetNnProcessor());
	}

	@Override
	public boolean process(Set<? extends TypeElement> arg0,
			RoundEnvironment arg1) {
		for(SpecificAnnotationTypeProcessor satp:processor){
			Class<? extends Annotation> notes=satp.getAnnotattionClass();
			Set<? extends Element> els=arg1.getElementsAnnotatedWith(notes);
			for(Element el:els){
				satp.process(el, arg1, processingEnv);
			}
		}
		return true;
	}

}

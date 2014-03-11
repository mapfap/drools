package com.mapfap;

import java.io.File;

import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

@SuppressWarnings({ "deprecation", "restriction" })
public class ExpertSystem {

	public static StatefulKnowledgeSession createExpertSystem() {
		try {
			KnowledgeBase kbase = createKnowledgeBaseFromSpreadsheet();
			StatefulKnowledgeSession kSession = kbase
					.newStatefulKnowledgeSession();
			@SuppressWarnings("unused")
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory
					.newFileLogger(kSession, "system");

//			Rice rice = new Rice();
//			rice.setFactor("ต้านทานโรคขอบใบแห้ง", Condition.TRUE);

//			kSession.insert(rice);
//			kSession.fireAllRules();
//			kSession.dispose();

//			System.out.println(rice.getResultList());
			return kSession;

		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	private static KnowledgeBase createKnowledgeBaseFromSpreadsheet() {

		DecisionTableConfiguration dtconf = KnowledgeBuilderFactory
				.newDecisionTableConfiguration();
		dtconf.setInputType(DecisionTableInputType.XLS);
		dtconf.setWorksheetName("Tables");
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
//		String xxx = "/Users/mapfap/Desktop/SocketServer/output.xls";
		Resource r = ResourceFactory.newFileResource(new File("Rules.xls"));
//		Resource r = ResourceFactory.newFileResource(new File(xxx));

		// System.out.println("XLS ResouceBuilder.");
		kbuilder.add(r, ResourceType.DTABLE, dtconf);
		// System.out.println("Loaded Resource File.");

		if (kbuilder.hasErrors()) {
			throw new RuntimeException(kbuilder.getErrors().toString());
		}
		KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return knowledgeBase;
	}

}

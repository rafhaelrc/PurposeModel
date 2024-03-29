package mas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
//import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import cartago.Artifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;
import cartago.manual.syntax.Term;
import jason.asSyntax.Literal;
import oo.OntoQueryLayer;
import oo.OntoQueryLayerBoolean;
import oo.OntoQueryLayerString;
import oo.OwlOntoLayer;
import oo.Util;




public class OntologyArtifact extends Artifact {
	private Logger logger = Logger.getLogger(OntologyArtifact.class.getName());

	private OwlOntoLayer onto = null;
	private OntoQueryLayerLiteral queryEngine;
	private OntoQueryLayerString queryEngineString;
	private OntoQueryLayerBoolean queryEngineBoolean;
	private OntoQueryLayer queryEngineLayer;


	void init(String ontologyPath) {
		logger.info("Importing ontology from " + ontologyPath);
		try {
			this.onto = new OwlOntoLayer(ontologyPath);

			// Load HermiT reasoner
			OWLReasoner reasoner = new Reasoner.ReasonerFactory().createReasoner(onto.getOntology());
			onto.setReasoner(reasoner);

			// OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
			// this.onto.setReasoner(reasonerFactory.createReasoner(this.onto.getOntology()));

			queryEngine = new OntoQueryLayerLiteral(this.onto);
			queryEngineLayer = new OntoQueryLayer(this.onto);
			queryEngineString = new OntoQueryLayerString(this.onto);
			queryEngineBoolean = new OntoQueryLayerBoolean(this.onto);

			logger.info("Ontology ready!");
		} catch (OWLOntologyCreationException e) {
			logger.info("An error occurred when loading the ontology. Error: " + e.getMessage());
		} catch (Exception e) {
			logger.info("An unexpected error occurred: " + e.getMessage());
		}
	}

	/**
	 * @param instanceName Name of the new instance.
	 * @param conceptName  Name of the concept which the new instance instances.
	 */
	@OPERATION
	void addInstance(String instanceName, String conceptName) {
		queryEngine.getQuery().addInstance(instanceName, conceptName);
	}

	/**
	 * @param instanceName Name of the new instance.
	 */
	@OPERATION
	void addInstance(String instanceName) {
		queryEngine.getQuery().addInstance(instanceName);
	}

	/**
	 * @param instanceName Name of the instance.
	 * @param conceptName  Name of the concept.
	 * @return true if the <code>instanceName</code> instances
	 *         <code>conceptName</code>.
	 */
	@OPERATION
	void isInstanceOf(String instanceName, String conceptName, OpFeedbackParam<Boolean> isInstance) {
		isInstance.set(queryEngine.getQuery().isInstanceOf(instanceName, conceptName));
	}

	/**
	 * @param conceptName Name of the concept.
	 * @param instances   A free variable to receive the list of instances in the
	 *                    form of instances(concept,instance)
	 */
	@OPERATION
	void getInstances(String conceptName, OpFeedbackParam<Literal[]> instances) {
		List<Object> individuals = queryEngine.getIndividualNames(conceptName);
		instances.set(individuals.toArray(new Literal[individuals.size()]));
	}

	/**
	 * @return A list of ({@link OWLObjectProperty}).
	 */
	@OPERATION
	void getObjectPropertyNames(OpFeedbackParam<Literal[]> objectPropertyNames) {
		List<Object> names = queryEngine.getObjectPropertyNames();
		objectPropertyNames.set(names.toArray(new Literal[names.size()]));
	}


	/**
	 * @param domainName   Name of the instance ({@link OWLNamedIndividual}} which
	 *                     represent the property <i>domain</i>.
	 * @param propertyName Name of the new property.
	 * @param rangeName    Name of the instance ({@link OWLNamedIndividual}} which
	 *                     represent the property <i>range</i>.
	 */
	@OPERATION
	void addProperty(String domainName, String propertyName, String rangeName) {
		queryEngine.getQuery().addProperty(domainName, propertyName, rangeName);
	}

	/**
	 * @param domainName   Name of the instance which represents the domain of the
	 *                     property.
	 * @param propertyName Name of the property.
	 * @param rangeName    Name of the instance which represents the range of the
	 *                     property.
	 * @return true if a instance of the property was found, false otherwise.
	 */
	@OPERATION
	void isRelated(String domainName, String propertyName, String rangeName, OpFeedbackParam<Boolean> isRelated) {
		isRelated.set(queryEngine.getQuery().isRelated(domainName, propertyName, rangeName));
	}


	/**
	 * @param domain       The name of the instance which corresponds to the domain
	 *                     of the property.
	 * @param propertyName Name of the property
	 * @return A list of ({@link OWLNamedIndividual}).
	 */
	@OPERATION
	void getObjectPropertyValues(String domain, String propertyName, OpFeedbackParam<Literal[]> instances) {
		List<Object> individuals = queryEngine.getObjectPropertyValues(domain, propertyName);
		instances.set(individuals.toArray(new Literal[individuals.size()]));
	}

	/**
	 * @return A list of ({@link OWLClass}).
	 */
	@OPERATION
	void getClassNames(OpFeedbackParam<Literal[]> classes) {
		List<Object> classNames = queryEngine.getClassNames();
		classes.set(classNames.toArray(new Literal[classNames.size()]));
	}

	/**
	 * @param conceptName Name of the new concept.
	 */
	@OPERATION
	void addConcept(String conceptName) {
		queryEngine.getQuery().addConcept(conceptName);
	}

	/**
	 * @param subConceptName   Name of the supposed sub-concept.
	 * @param superConceptName Name of the concept to be tested as the
	 *                         super-concept.
	 * @return true if <code>subConceptName</code> is a sub-concept of
	 *         <code>sueperConceptName</code>, false otherwise.
	 */
	@OPERATION
	void isSubConcept(String subConceptName, String superConceptName, OpFeedbackParam<Boolean> isSubConcept) {
		isSubConcept.set(queryEngine.getQuery().isSubConceptOf(subConceptName, superConceptName));
	}

	/**
	 * @param outputFile Path to the new file in the structure of directories.
	 * @throws OWLOntologyStorageException
	 */
	@OPERATION
	void saveOntotogy(String outputFile) {
		try {
			queryEngine.getQuery().saveOntology(outputFile);
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return A list of ({@link OWLAnnotationProperty}).
	 */
	@OPERATION
	void getAnnotationPropertyNames(OpFeedbackParam<Literal[]> AnnotationPropertyNames) {
		List<Object> names = queryEngine.getAnnotationPropertyNames();
		AnnotationPropertyNames.set(names.toArray(new Literal[names.size()]));
	}

	/**
	 * @return A list of ({@link OWLDataProperty}).
	 */
	@OPERATION
	void getDataPropertyNames(OpFeedbackParam<Literal[]> dataPropertyNames) {
		List<Object> names = queryEngine.getDataPropertyNames();
		dataPropertyNames.set(names.toArray(new Literal[names.size()]));
	}

	
	@OPERATION
	void teste(String teste) {
		System.out.println("Só testar.." + teste);
	}
	
/**  Methods above were delivered with the MasOntology */	

/**  Methods below were created for use the Propose Model */
	
	/**
	 * create a predicate
	 * check if this predicate there is in the ontology
	 * if true,
	 * check if this predicate are related to a state of the system
	 * if true,
	 * get the state
	 * looking for purposes that are related to state.
	 * @param Object predicate
	 * @return List<Strig> of purposes
	 */

	@OPERATION
	void getPurposesOfState(Object predicate, OpFeedbackParam<String[]> purposes) {
		//Literal predicate2 = Util.createLiteral(predicate); own method to convert strint to literal
		Literal predicate2 = Literal.parseLiteral(predicate.toString());

		ArrayList<String> arrayStates   = new ArrayList<>();
		ArrayList<String> arrayPurposes = new ArrayList<>();

		if(queryEngineBoolean.thereIsAPredicateInOntology(predicate2)) {
			arrayStates =  queryEngineString.getStatesByPredicate(predicate2);
			for (String state : arrayStates) {
				for(String purpose : queryEngineString.getPurposesByState(state)) {
					arrayPurposes.add(purpose);
				}
			}
			purposes.set(Util.convertArrayListOfStringinArrayofString(arrayPurposes));
		}
		else {
			System.out.println("The terms of the predicate are incorrect.");
			purposes.set(Util.convertArrayListOfStringinArrayofString(arrayPurposes));
		}
	}

	
	
	/**
	 * create a predicate
	 * check if this predicate there is in the ontology
	 * if true,
	 * check if this predicate are related to a state of the system
	 * if true,
	 * get the state
	 * looking for ---- one purpose ---- that are related to state.
	 * @param Object predicate
	 * @return one Purpose
	 */
	@OPERATION
	void getPurposeOfState(Object predicate, OpFeedbackParam<String> purpose) {
		Literal predicate2 = Literal.parseLiteral(predicate.toString());

		ArrayList<String> arrayStates   = new ArrayList<>();
		//ArrayList<String> arrayPurposes = new ArrayList<>();

		if(queryEngineBoolean.thereIsAPredicateInOntology(predicate2)) {
			arrayStates =  queryEngineString.getStatesByPredicate(predicate2);
			for (String state : arrayStates) {
				purpose.set(queryEngineString.getPurposeByState(state));
			}
			
		}
		else {
			System.out.println("The terms of the predicate are incorrect.");
			purpose.set(null);
		}
	}
	
	
	/**
	 * Methods relate to purposes and Status-FUnctions
	 */
	
	
	/**
	 * Method to get one (only one) Status-Function relate to one (only one) purpose informed in input.
	 * @param Object Purposes
	 * @return String of StatusFunction
	 */

	@OPERATION
	void getStatusFunctionFromPurpose(Object purpose, OpFeedbackParam<String> statusFunction) {
		//ArrayList<String> statusFunctions = new ArrayList<>();
		for(String sf : queryEngineString.getStatusFunctionsByPurpose(String.valueOf(purpose))) {
			statusFunction.set(sf);
		}
	}
	
	
	
	/**
	 * Method to get Status-Functions relate to one (only one) purpose informed in input.
	 * @param Object Purpose
	 * @return List<String> of StatusFunctions
	 */

	@OPERATION
	void getStatusFunctionsFromPurpose(Object purpose, OpFeedbackParam<String[]> statusFunctionNames) {
		ArrayList<String> statusFunctions = new ArrayList<>();
		for(String sf : queryEngineString.getStatusFunctionsByPurpose(String.valueOf(purpose))) {
			statusFunctions.add(sf);
		}
		statusFunctionNames.set(Util.convertArrayListOfStringinArrayofString(statusFunctions));
	}
	
	
	
	/**
	 * Method to get Status-Functions relate to List of purposes informed in input.
	 * @param List<Object> of Purposes
	 * @return List<String> of StatusFunctions
	 */

	@OPERATION
	void getStatusFunctionsFromPurposes(Object[] purposes, OpFeedbackParam<String[]> statusFunctionNames) {
		ArrayList<String> statusFunctions = new ArrayList<>();
		for(Object objPurpose : purposes) {
			for(String sf : queryEngineString.getStatusFunctionsByPurpose(String.valueOf(objPurpose))) {
				statusFunctions.add(sf);
			}
		}
		statusFunctionNames.set(Util.convertArrayListOfStringinArrayofString(statusFunctions));
	}



	@OPERATION
	void getPurposesOfStatusFunctions(Object statusFunction, OpFeedbackParam<String[]> purposes) {
		ArrayList<String> arrayPurpose = new ArrayList<>();
		for(String purpose : queryEngineString.getPurposesByStatusFunctions(String.valueOf(statusFunction))) {
			//System.out.println("Purpose encontrado: " + purpose);
			arrayPurpose.add(purpose);
		}
		purposes.set(Util.convertArrayListOfStringinArrayofString(arrayPurpose));
	}


	/**
	 * get a list of purposes and return a list of the states related to purposes.
	 * @param purposes
	 * @param states
	 * PRECISA CONSERTAR. EU TO PEGANDO SOMENTE O PRIMEIRO PROP�SITO DA LISTA DE OBJETO E BUSCANDO OS STATES.
	 * SE TIVER MAIS DE UM PROP�SITO NA LISTA, OS D+ S�O IGNORADOS.
	 */

	
	/*  */
	@OPERATION
	void isStateOfPurpose(Object[] purposes, OpFeedbackParam<String[]> states) {
		ArrayList<String> arrayStates = new ArrayList<>();
		ArrayList<String> arrayResultStates;
		arrayResultStates =  queryEngineString.getStatesByPurpose(purposes[0].toString());
		for (String state : arrayResultStates) {
			arrayStates.add(state);
			System.out.println("State: " + state);
			queryEngineString.getPredicatesByState(state);
		}
		states.set(Util.convertArrayListOfStringinArrayofString(arrayStates));
	}

	/**
	 *
	 * @param states
	 * @param predicates
	 */
	@OPERATION
	void isPredicateOfState(Object[] states, OpFeedbackParam<Literal[]> predicates) {
		ArrayList<Literal> returnPredicates = new ArrayList<>();
		returnPredicates = queryEngineString.getPredicatesByState(states[0].toString());
		predicates.set(Util.convertArrayListOfLiteralinArrayofLiteral(returnPredicates));
	}
   
	
	
	/**
	 * 
	 * @param purpose
	 * @param predicates
	 */
	@OPERATION
    void getPredicatesOfStatesRelatedToPurpose(Object purpose, OpFeedbackParam<Literal[]> predicates) {
		
		/*
		 * List<Object> individuals = queryEngine.getIndividualNames(conceptName);
		instances.set(individuals.toArray(new Literal[individuals.size()]));
		 */
		
		
		ArrayList<Literal> returnPredicates = new ArrayList<>();
		ArrayList<String> arrayResultStates;
		arrayResultStates =  queryEngineString.getStatesByPurpose(purpose.toString());
		for (String state : arrayResultStates) {
			for(Literal predicate: queryEngineString.getPredicatesByState(state)) {
				returnPredicates.add(predicate); // pq um estado pode ter n predicados.
			}
			//queryEngineString.getPredicatesByState(state);
		}
		// ta dando problema aqui no retorno do literal
		predicates.set(returnPredicates.toArray(new Literal[returnPredicates.size()]));
	}	
	


}

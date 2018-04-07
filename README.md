# AREva
AREva - A toolset for *A*rchitecture *R*elation *Eva*luation

## Idea, Origin, State and on-going Work
This EMF-based prototypical toolset implements a theoretical framework for the evaluation of architecture relations, proposed in the PhD thesis of L. Maertin. 

The original implementation, AREva, was originally developed as Java application in course of the diploma thesis of L. Märtin in 2009 on basis of the concepts in the PhD thesis of B. Florentz. At this time, AREva enabled automated evaluations and analyses of software/hardware architectures for AUTOSAR systems. Architectures were imported and validated towards the fulfillment of a finite set of quality attributes. For that, the user described the hierarchical order of that attributes and defined metrics and conversions to qualities (values to %). At least, AREva checked violations of lower bounds for all qualities and aggregates the overall quality of an architectural design. In addition, the tool supported the analysis of the distance relations between the distribution of computational resources and software components mapped to them.

The re-development was scratched and supervised by L. Märtin. S. v. Höveling started the development of the new EMF-implementation in 2014/15 as master student assistant for the TU Braunschweig (Institute for Programming and Reactive Systems, IPS). It was implemented as EMF project to make use of established plugins for meta-modelling and visualisation. In particular, the new version of AREva enables the use of the tool in combination with the Palladio Bench modelling tool (http://www.palladio-simulator.com) to import architectures in the Palladio Component Model format. Aside with the Palladio extension PerOpteryx (https://sdqweb.ipd.kit.edu/wiki/PerOpteryx) the generation and evaluation of a set of variations from a base architecture is possible. Here, AREva supports the user to figure out the best-fitting variation, automated derived by the evaluation structure (Trade-off determination). The evaluation structure is now capable to describe the quality demands of an operational mode with weightings and minimal acceptance values. However, the user has to post-process the results from the architecture evaluation by hand.

The on-going development and the further integration of the tool chain are done by L. Märtin and N.-A. Forjahn as bachelor student assistant at IPS. In particular, the relations between variants and the post-processing of evaluation results are inspected here to use that additional knowledge from design time to support reconfiguration decisions at run time. The tool is extended as expert system to support maintenance of dependable systems. The gathered date is post-processed by the tool an structured by several export files (result.xml for reconfiguration paths, several csv for graph-wide data, ...) to provide rationales for reconfigration decisions.

The third extension of AREva was done in course of the bachelor thesis of N.-A. Forjahn. The thesis is about to support multi-purpose scenarios with varying operational mode of a system. Thus, the quality demands a no longer fixed, but several can be defined. The refined implementation of AREva is now capable to (1) support the analysis of multiple modes, (2) figure out commonalities between nodes for best transition locations in graphs and (3) deliver an optimal order of modes for the life cycle of the sytem.

## Features
The current version of the AReva toolset supports:
- Definition of the evaluation tree structure
  - Hierarchical ordering
  -	Weightings
  - Worst case acceptance values
  -	Mapping to metrics and quality rates
-	Graphical representation of the evaluation structure
  -	Tree-based w/o cross-cutting constraints
  -	Partial highlighting of results and violations
-	Definition of metrics
  -	Java-Code
  -	Templates for frequently queries on the architecture 
  -	Syntax highlighting
-	Definition of Quality Rates
  -	Mathematical functions with intervals
  -	Tables for discrete value conversion
  -	Graphical representation of the functions
-	Architecture importers
  -	Automotive architectures in ARXML-format
  -	Architectures of the Palladio Component Model
- Multi-mode support
  - Batch-processing of analyses for several operation mode
  - Derivation of optimal transition configurations
  - Optimal Mode Sequencing
  
## Requirements
- Palladio 4.x
  - Palladio Bench
  - Palladio Design Space Exporation (PerOpteryx)
  - Install core features wrt. https://sdqweb.ipd.kit.edu/wiki/PCM_4.1#Release_Update_Site
- Use changed Palladio Plugins from this repo. to integrate AREva functions; some extensions must be hardcoded in the current release of DSE
  
In addition to Palladio Bench and Palladio DSE/PerOpteryx, AREva makes use of several libraries: GEF4/Zest, Opt4J, Google Guice, jep (Java Expression Parser), ... (list to be completed).

## Validation Data
- Folder: validation_data
- Contents: All validation data for the single and multi mode analyses 
- Includes a short How-To for re-doing the validation

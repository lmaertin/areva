package de.tubs.areva.util;

import de.tubs.areva.resourcerelations.And;
import de.tubs.areva.resourcerelations.Expression;
import de.tubs.areva.resourcerelations.Implies;
import de.tubs.areva.resourcerelations.Not;
import de.tubs.areva.resourcerelations.Or;
import de.tubs.areva.resourcerelations.Platform;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.resourcerelations.ResourceOptionsVariable;
import de.tubs.areva.resourcerelations.ResourceVariable;
import de.tubs.areva.resourcerelations.Rule;

public class RuleChecker {

	/**
	 * Check all constraints in the rules of the platform model.
	 * @return
	 */
	public static boolean checkAllRules(Platform resourceRelations, ArevaIndividual individual){
		boolean result = true;
		//Example:
		//<Rules Name="CSS_dummy -> ASC low OR ASC high">
		//  <Expression LeftHandSideModifier="true">
		//	  <LeftHandSide ResourceGroup="//@groups.7"/>
		//    <RightHandSide xsi:type="arr:Or">
		//	    <LeftHandSide xsi:type="arr:ResourceGroupVariable" ResourceGroup="//@groups.5"/>
		//	    <RightHandSide xsi:type="arr:ResourceGroupVariable" ResourceGroup="//@groups.6"/>
		//	  </RightHandSide>
		//	</Expression>
		//</Rules>
		for(Rule rule : resourceRelations.getRules()){
			//				System.out.println("Rule " + rule.getName() + ":");
			Implies implies = rule.getExpression();
			ResourceOptions rgLeft = implies.getLeftHandSide().getResourceOptions();
			Expression expr = implies.getRightHandSide();
			if(individual.getAffectedResourceGroups().contains(rgLeft)){
				//					System.out.print(rgLeft.getName() + "->");
				result = checkExpression(expr, individual); //Check right hand side for "rgLeft -> ..."
			}
			else{
				if(implies.isLeftHandSideModifier()){
					result = true; //rule is irrelevant, this resource group is not considered
					//						System.out.println("(not checked, " + rgLeft.getName() + " is not considered in candidate here.)");
				}
				else{ //false is Not
					//						System.out.print("!" + rgLeft.getName() + "->");
					result = checkExpression(expr, individual); //Check right hand side for "!rgLeft -> ..."
				}
			}
			//				System.out.println(" ==> " + result);

			if(!result){
				//					System.out.println("stop checking; at least 1 rule failed.");
				break; 
			}
		}

		return result;
	}

	/**
	 * Recursively check each constraint
	 * @param expr
	 * @return
	 */
	private static  boolean checkExpression(Expression expr, ArevaIndividual individual){
		if(expr instanceof And){
			And and = (And)expr;
			boolean left = checkExpression(and.getLeftHandSide(), individual);
			//				System.out.print(" & ");
			boolean right = checkExpression(and.getRightHandSide(), individual);
			return left && right;
		}
		else if(expr instanceof Or){
			Or or = (Or)expr;
			boolean left = checkExpression(or.getLeftHandSide(), individual);
			//				System.out.print(" | ");
			boolean right = checkExpression(or.getRightHandSide(), individual);
			return left || right;
		}
		else if (expr instanceof Not){
			Not not = (Not)expr;
			//				System.out.print("!");
			return !checkExpression(not.getExpression(), individual);
		}
		else if (expr instanceof ResourceOptionsVariable){
			ResourceOptionsVariable rqVarExpr = (ResourceOptionsVariable)expr;
			ResourceOptions varRq = rqVarExpr.getResourceOptions();
			if(individual.getAffectedResourceGroups().contains(varRq)){
				//					System.out.print(varRq.getName() + "(true)");
				return true;
			}
			else{
				//					System.out.print(varRq.getName() + "(false)");
				return false;
			}
		}
		else if (expr instanceof ResourceVariable){
			ResourceVariable resVarExpr = (ResourceVariable)expr;
			Resource varRes = resVarExpr.getResource();
			boolean isInSet = false;
			for (ResourceOptions rq : individual.getAffectedResourceGroups()){
				for(Resource res : rq.getResources()){
					if(varRes == res){
						isInSet = true;
					}
					else{
						isInSet = false;
					}
				}
			}
			//				System.out.print(varRes.getName()  + "("+ isInSet + ")");
			return isInSet;
		}
		else{
			//				System.out.print("else (false)");
			return false;
		}
	}
	
}

package org.aksw.limes.core.execution.planning.planner;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.aksw.limes.core.execution.planning.plan.Instruction;
import org.aksw.limes.core.execution.planning.plan.Instruction.Command;
import org.aksw.limes.core.execution.planning.plan.NestedPlan;
import org.aksw.limes.core.io.ls.ExtendedLinkSpecification;
import org.aksw.limes.core.io.ls.LinkSpecification;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CanonicalPlannerTest {

    private static final Logger logger = LoggerFactory.getLogger(CanonicalPlannerTest.class);
    @Test
    public void EmptyPlan() {
        logger.info("{}","EmptyPlan");

        CanonicalPlanner p = new CanonicalPlanner();
        LinkSpecification ls = new LinkSpecification();
        NestedPlan plan = p.plan(ls);
        assertTrue(plan.isEmpty() == true);
    }

    @Test
    public void AtomicPlan() {
        logger.info("{}","AtomicPlan");

        CanonicalPlanner p = new CanonicalPlanner();
        LinkSpecification ls = new LinkSpecification("jaccard(x.surname, y.surname)", 0.8);
        NestedPlan plan = p.plan(ls);
        assertTrue(plan.isEmpty() == false);
        assertTrue(plan.isAtomic() == true);

        // atomic plans have instructions lists
        assertTrue(plan.getInstructionList().isEmpty() == false);
        // atomic plans don't have subplans
        assertTrue(plan.getSubPlans() == null);
        // atomic plans don't have filteringinstructions
        assertTrue(plan.getFilteringInstruction() == null);

    }

    @Test
    public void ComplexPlanLS() {
        logger.info("{}","ComplexPlanLS");

        CanonicalPlanner p = new CanonicalPlanner();
        LinkSpecification ls = new LinkSpecification(
                "OR(jaccard(x.title,y.name)|0.5941,OR(XOR(OR(XOR(trigrams(x.description,y.description)|0.7728,qgrams(x.title,y.name)|0.6029)|0.7728,XOR(trigrams(x.description,y.description)|0.7728,qgrams(x.title,y.name)|0.6029)|0.7728)|0.5807,OR(XOR(trigrams(x.description,y.description)|0.7728,qgrams(x.title,y.name)|0.6029)|0.7728,trigrams(x.title,y.name)|0.5919)|0.5807)|0.7728,trigrams(x.description,y.description)|0.7728)|0.5807)",
                0.8);
        NestedPlan plan = p.plan(ls);
        assertTrue(plan.isEmpty() == false);
        assertTrue(plan.isAtomic() == false);

        // atomic plans have instructions lists = the instructions lists of
        // their subplans if any
        assertTrue(plan.getInstructionList().isEmpty() == false);
        assertTrue(plan.getInstructionList().size() == 9);

        // atomic plans have subplans
        assertTrue(plan.getSubPlans().isEmpty() == false);
        // atomic plans have filteringinstructions
        assertTrue(plan.getFilteringInstruction() != null);
    }

    @Test
    public void ComplexPlanExtendedLS() {
        logger.info("{}","ComplexPlanExtendedLS");

        CanonicalPlanner p = new CanonicalPlanner();
        ExtendedLinkSpecification ls = new ExtendedLinkSpecification(
                "OR(jaccard(x.title,y.name)|0.5941,OR(XOR(OR(XOR(trigrams(x.description,y.description)|0.7728,qgrams(x.title,y.name)|0.6029)|0.7728,XOR(trigrams(x.description,y.description)|0.7728,qgrams(x.title,y.name)|0.6029)|0.7728)|0.5807,OR(XOR(trigrams(x.description,y.description)|0.7728,qgrams(x.title,y.name)|0.6029)|0.7728,trigrams(x.title,y.name)|0.5919)|0.5807)|0.7728,trigrams(x.description,y.description)|0.7728)|0.5807)",
                0.8);

        logger.info("{}",ls.getFullExpression());
        NestedPlan plan = p.plan(ls);
        assertTrue(plan.isEmpty() == false);
        assertTrue(plan.isAtomic() == false);

        // atomic plans have instructions lists = the instructions lists of
        // their subplans if any
        assertTrue(plan.getInstructionList().isEmpty() == false);
        assertTrue(plan.getInstructionList().size() > 9);

        // atomic plans have subplans
        assertTrue(plan.getSubPlans().isEmpty() == false);
        // atomic plans have filteringinstructions
        assertTrue(plan.getFilteringInstruction() != null);
    }

    @Test
    public void PlanWithWrongOperator() {
        logger.info("{}","PlanWithWrongOperator");

        CanonicalPlanner p = new CanonicalPlanner();

        LinkSpecification ls = null;
        try {
            ls = new LinkSpecification(
                    "blabla(jaccard(x.surname, y.surname)|0.5,cosine(x.label,y.label)|0.6)", 0.8);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        NestedPlan plan = p.plan(ls);
        assertTrue(plan != null);

    }

    @Test
    public void AtomicEqual() {
        logger.info("{}","AtomicEqual");

        CanonicalPlanner p = new CanonicalPlanner();

        LinkSpecification ls = new LinkSpecification("cosine(x.label,y.label)", 0.8);
        logger.info("{}",ls.isAtomic());

        NestedPlan plan = p.plan(ls);

        NestedPlan plan2 = new NestedPlan();
        Instruction run1 = new Instruction(Command.RUN, "cosine(x.label,y.label)", "0.8", -1, -1, 0);
        plan2.addInstruction(run1);

        assertTrue(plan.equals(plan2));
    }

    @Test
    public void ComplexEqual() {
        logger.info("{}","ComplexEqual");

        CanonicalPlanner p = new CanonicalPlanner();

        LinkSpecification ls = new LinkSpecification(
                "OR(cosine(x.description,y.description)|0.3,OR(cosine(x.description,y.description)|0.5,cosine(x.title,y.name)|0.6)|0.7)",
                0.8);
        logger.info("{}",ls.isAtomic());

        NestedPlan plan = p.plan(ls);

        NestedPlan plan2 = new NestedPlan();
        Instruction run1 = new Instruction(Command.RUN, "cosine(x.description,y.description)", "0.3", -1, -1, 0);
        plan2.addInstruction(run1);
        //////////////////////////////////////////////////////////////////////////////////
        NestedPlan plan3 = new NestedPlan();

        NestedPlan plan31 = new NestedPlan();
        Instruction run2 = new Instruction(Command.RUN, "cosine(x.description,y.description)", "0.5", -1, -1, 0);
        plan31.addInstruction(run2);
        NestedPlan plan32 = new NestedPlan();
        Instruction run3 = new Instruction(Command.RUN, "cosine(x.title,y.name)", "0.6", -1, -1, 0);
        plan32.addInstruction(run3);

        plan3.setSubPlans(new ArrayList<NestedPlan>());
        plan3.addSubplan(plan31);
        plan3.addSubplan(plan32);

        plan3.setOperator(Command.UNION);
        plan3.setFilteringInstruction(new Instruction(Command.FILTER, null, "0.7", -1, -1, 0));

        /////////////////////////////////////////////////////////////////////////////////////
        NestedPlan planNew = new NestedPlan();
        planNew.setSubPlans(new ArrayList<NestedPlan>());
        planNew.addSubplan(plan2);
        planNew.addSubplan(plan3);
        planNew.setOperator(Command.UNION);
        planNew.setFilteringInstruction(new Instruction(Command.FILTER, null, "0.8", -1, -1, 0));

        assertTrue(plan.equals(planNew));
    }

}

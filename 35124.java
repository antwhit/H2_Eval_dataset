import engine.Engine;
import generate.GraphTaskAssociations;
import generate.graph.ReadGraph;
import generate.node.ReadNodeConfig;
import generate.taskmap.ReadTaskMap;

import org.xml.sax.helpers.DefaultHandler;

import pe.PE;
import pe.task.Task;

import java.util.Vector;

public class NocSim extends DefaultHandler
{
    public static void main(String argv[])
    {
       if (argv.length != 3) {
            System.err.println("Usage: nocsim <graphfilename> <nodeconfigfilename> <taskmapfilename>");
            System.exit(1);
        }
        
        // Read configuration files.
    	ReadGraph graph           = new ReadGraph(argv[0]);
    	ReadNodeConfig nodeConfig = new ReadNodeConfig(argv[1]);
    	ReadTaskMap taskmap       = new ReadTaskMap(argv[2]);
    	
    	Engine engine = new Engine(graph.getNumberOfGraphs());
    	
    	// Populate graph tasks.
    	GraphTaskAssociations graphs[] = new GraphTaskAssociations[graph.getNumberOfGraphs()];
    	for (int index1 = 0; index1 < graph.getNumberOfGraphs(); index1++) {
    		generate.graph.Graph graphXML = graph.getGraphNumber(index1);
    		graphs[index1] = new GraphTaskAssociations(graphXML.getID(), engine);
    		
        	for (int index2 = 0; index2 < graphXML.getNumberOfTasks(); index2++) {
        		generate.graph.Task taskXML = graphXML.getTaskNumber(index2);
        		graphs[index1].addTask(taskXML.getID(), taskXML.getDelay(), taskXML.getNumberOfPackets());
        		
            	if (taskXML.getID() == 0) {						// Root task.
                	for (int index3 = 0; index3 < taskXML.getNumberOfPackets(); index3++) {
                		generate.graph.Packet packetXML = taskXML.getPacketNumber(index3);
                		graphs[index1].addTriggertoRootTask(taskXML.getID(), packetXML.getID(), packetXML.getSize());
                	}
            	} else {
            		graphs[index1].createTaskUniquePacketIDSet(taskXML.getNumberOfTriggers());
            		
                	for (int index3 = 0; index3 < taskXML.getNumberOfTriggers(); index3++) {
                		generate.graph.Trigger triggerXML = taskXML.getTriggerNumber(index3);
                		graphs[index1].addTaskUniquePacketIDtoSet(triggerXML.getTaskID(), triggerXML.getPacketID());
                	}

                	if (taskXML.getNumberOfPackets() > 0) {		// Interior.
                		for (int index3 = 0; index3 < taskXML.getNumberOfPackets(); index3++) {
                    		generate.graph.Packet packetXML = taskXML.getPacketNumber(index3);
                    		graphs[index1].addTriggertoInteriorTask(taskXML.getID(), packetXML.getID(), packetXML.getSize());
                		}
                	} else {									// Leaf.
                		graphs[index1].addTriggertoLeafTask(graphXML.getID(), taskXML.getID());
                	}
               	}
        	}
        	
        	// Calculate sink shit.
        	graphs[index1].graphFinalize();
    	}

    	// Create PEs.
    	PE processingElements[] = new PE[nodeConfig.getNumberOfNodes()];
    	for (int index1 = 0; index1 < nodeConfig.getNumberOfNodes(); index1++) {
    		processingElements[index1] = new PE(engine, graph.getNumberOfGraphs());
    	}
    	
    	// Populate PEs.
    	
    	// index1 indicates current graph.
    	for (int index1 = 0; index1 < graphs.length; index1++) {
    		// index2 indicates current graph task.
        	for (int index2 = 0; index2 < graphs[index1].getNumberOfGraphTasks(); index2++) {
        		boolean taskPEfound = false;
        		
        		int index3 = 0;
        		int index4 = 0;
        		while (index3 < nodeConfig.getNumberOfNodes()) {
            		generate.node.Node nodeXML = nodeConfig.getNodeNumber(index3);
            		while (index4 < nodeXML.) {
            			
            		}
        		}
        	}
    	}
    	
    	System.exit(0);
    }

}
/*
 * This file is part of the Panini project at Iowa State University.
 *
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/.
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * 
 * For more details and the latest version of this code please see
 * http://www.cs.iastate.edu/~design/projects/panini/
 *
 * Contributor(s):
 */


import java.util.Hashtable;

/**
 * 
 *
 */
public class GlasbeyThreshold{

    private Hashtable<Image, Integer> values;
    
    
    public GlasbeyThreshold(){
        register(this);
        values = new Hashtable<Image, Integer>();
    }

    when Changed do compute;

    /**
     * Compute the GlasbeyThreshold when a Changed event happens.
     * @param rest
     * @param pic
     */
    public void compute(Changed rest, Image pic){
        int threshold = -1;
        double tot = 0, sum=0;
        int[] data = pic.getPixels();
        for (int i=0; i<256; i++){
            tot+= data[i];
            sum+=(i*data[i]);
        }
        threshold =(int) Math.floor(sum/tot);
        values.put(pic, threshold);
    }
    
    public Hashtable<Image, Integer> getValues(){
        return values;
    }
}

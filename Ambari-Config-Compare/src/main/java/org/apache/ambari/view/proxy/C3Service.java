/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ambari.view.proxy;

import org.apache.ambari.view.AmbariHttpException;
import org.apache.ambari.view.ViewContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;


/**
 * Configuration service for accessing Ambari REST API for capacity scheduler config.
 *
 */
public class C3Service {

//  private static final Logger LOG = LoggerFactory.getLogger(C3Service.class);

  @Inject
  ViewContext context;

  /**
   * Constructor.
   
   * @param context     the ViewContext instance (may not be <code>null</code>)
   */
  public C3Service() {
  }


  private static final String RM_GET_SCHEDULER_CONFIG = "%s/ws/v1/cluster/scheduler";

  /**
   * Gets capacity scheduler configuration.
   *
   * @return scheduler configuration
   */

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/compare")
  public Response getConfigurationValue(@QueryParam("siteName") String siteName,@QueryParam("configName") String configName){
    //LOG.info("Get configuration value for siteName {}, configName {}", siteName, configName);
    JSONObject res=null;
    try{
      //String configValue = context.getCluster().getConfigurationValue(siteName,configName);
    res = new JSONObject();
      JSONArray arr = new JSONArray();
      JSONObject conf = new JSONObject();
      conf.put("siteName",siteName);
      conf.put("configName", configName);
      //conf.put("configValue", configValue);
      arr.add(conf);
      res.put("configs" ,arr);
      //return Response.ok(res).build();
    } catch (WebApplicationException ex) {
      //LOG.error("Exception occurred : ", ex);
      //throw ex;
    } catch (Exception ex) {
      //LOG.error("Exception occurred : ", ex);
      //throw new Exception(ex.getMessage(), ex);
    }
    return Response.ok(res).build();
  }
  

} // end C3Service

//http://birens-hdp0.field.hortonworks.com:8080/api/v1/views/CAPACITY-SCHEDULER/versions/1.0.0/instances/AUTO_CS_INSTANCE/resources/scheduler/configuration/nodeLabels
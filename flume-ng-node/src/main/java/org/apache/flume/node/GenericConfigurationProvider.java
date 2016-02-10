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

package org.apache.flume.node;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.flume.conf.FlumeConfiguration;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * ZooKeeper based configuration implementation provider.
 * 
 * The Agent configuration can be uploaded in ZooKeeper under a base name, which
 * defaults to /flume
 * 
 * Currently the agent configuration is stored under the agent name node in
 * ZooKeeper
 * 
 * <PRE>
 *   /flume
 *       /a1 [agent config file]
 *       /a2 [agent config file]
 *       /a3 [agent config file]
 * </PRE>
 * 
 * Configuration format is same as PropertiesFileConfigurationProvider
 * 
 * Configuration properties
 * 
 * agentName - Name of Agent for which configuration needs to be pulled
 * 
 * zkConnString - Connection string to ZooKeeper Ensemble
 * (host:port,host1:port1)
 * 
 * basePath - Base Path where agent configuration needs to be stored. Defaults
 * to /flume
 */
public abstract class GenericConfigurationProvider extends
    AbstractConfigurationProvider {

  protected final CommandLine commandLine;


  protected GenericConfigurationProvider(String agentName,
          CommandLine commandLine) {
    super(agentName);
    this.commandLine=commandLine;
  }

    protected abstract FlumeConfiguration getFlumeConfiguration();

}

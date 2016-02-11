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
 * Generic based configuration implementation provider.
 * 
 * You can implement your own class passing a parameter whit needs that you have.
 *
 * Configuration format is same as PropertiesFileConfigurationProvider
 * 
 * Configuration properties
 * 
 * agentName - Name of Agent for which configuration needs to be pulled
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

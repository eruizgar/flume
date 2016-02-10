/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.flume.node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.flume.conf.FlumeConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by eruiz on 9/02/16.
 */
public class MetadataConfigurationProvider extends GenericConfigurationProvider{
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MetadataConfigurationProvider.class);

    public MetadataConfigurationProvider(String agentName, CommandLine commandLine) {
        super(agentName, commandLine);
    }

    @Override
    public FlumeConfiguration getFlumeConfiguration() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(commandLine.getOptionValue('f')));
            Properties properties = new Properties();
            properties.load(reader);
            return new FlumeConfiguration(toMap(properties));
        } catch (IOException ex) {
            LOGGER.error("Unable to load file:" + commandLine.getOptionValue('f')
                    + " (I/O failure) - Exception follows.", ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    LOGGER.warn(
                            "Unable to close file reader for file: " + commandLine.getOptionValue('f'), ex);
                }
            }
        }
        return new FlumeConfiguration(new HashMap<String, String>());
    }
}

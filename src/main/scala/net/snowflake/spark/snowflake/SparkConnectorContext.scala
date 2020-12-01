/*
 * Copyright 2015-2020 Snowflake Computing
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.snowflake.spark.snowflake

import org.slf4j.LoggerFactory

object SparkConnectorContext {
  private[snowflake] val logger = LoggerFactory.getLogger(getClass)

  private var isConfigLogged = false

  private val locker = new Object

  // The system configuration is logged once.
  private[snowflake] def recordConfig(): Unit = {
    locker.synchronized {
      if (!isConfigLogged) {
        isConfigLogged = true
        logger.info(s"Spark Connector system config: " +
          s"${SnowflakeTelemetry.getClientConfig().toPrettyString}")
      }
    }
  }
}

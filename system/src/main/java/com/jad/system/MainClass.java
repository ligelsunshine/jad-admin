/*
 * Copyright (c) 2021-2021, jad (cxxwl96@sina.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jad.system;

import java.sql.SQLException;

/**
 * MainClass
 *
 * @author cxxwl96
 * @since 2021/10/16 22:18
 */
public class MainClass {
    public static void main(String[] args) throws SQLException {
        // final DataSourceConfig sourceConfig = new DataSourceConfig.Builder(
        //     "jdbc:mysql://localhost:3306/dev_jad_admin?useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&useSSL=true&allowMultiQueries=true&autoReconnect=true&useAffectRows=true",
        //     "root", "cxxwl96@sina.com").build();
        // final Connection conn = sourceConfig.getConn();
        // final PreparedStatement statement = conn.prepareStatement("select * from sys_user where username=?");
        // statement.setString(1, "administrator");
        // final ResultSet resultSet = statement.executeQuery();
        // final User user = BeanHandler.create(User.class).handle(resultSet);
        // System.out.println(user);
    }
}

/*
 * Copyright (c) 2021-2023, cxxwl96.com (cxxwl96@sina.com).
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

package com.jad.common.exception;

import org.springframework.security.access.AccessDeniedException;

/**
 * 未授权、没有权限或拒绝访问异常
 *
 * @author cxxwl96
 * @since 2023/9/17 17:15
 */
public class PermissionDeniedException extends AccessDeniedException {
    public PermissionDeniedException() {
        super("没有权限或拒绝访问");
    }

    public PermissionDeniedException(String msg) {
        super(msg);
    }

    public PermissionDeniedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
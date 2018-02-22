/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.internal.tasks.compile.processing;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * Decorates the filer to validate the correct behavior for {@link SingleOriginProcessor}s.
 */
class SingleOriginFiler extends IncrementalFiler {

    SingleOriginFiler(Filer delegate, Messager messager) {
        super(delegate, messager);
    }

    protected void checkGeneratedType(String generatedType, Set<String> orignatingTypes, Messager messager) {
        int size = orignatingTypes.size();
        if (size != 1) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Generated type '" + generatedType + "' must have exactly one originating element, but had " + size + ".");
        }
    }

}

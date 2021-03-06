/*
 * Copyright 2011-2014, by Vladimir Kostyukov and Contributors.
 *
 * This file is part of la4j project (http://la4j.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributor(s): -
 *
 */

package org.la4j.vector.operation;

import org.la4j.factory.Factory;
import org.la4j.vector.Vector;
import org.la4j.vector.operation.inplace.InPlaceVectorByValueMultiplication;
import org.la4j.vector.operation.inplace.InPlaceVectorByVectorMultiplication;
import org.la4j.vector.operation.inplace.InPlaceVectorToVectorAddition;
import org.la4j.vector.operation.ooplace.OoPlaceInnerProduct;
import org.la4j.vector.operation.ooplace.OoPlaceVectorByValueMultiplication;
import org.la4j.vector.operation.ooplace.OoPlaceVectorToVectorAddition;

public final class VectorOperations {

    public static VectorVectorOperation<Double> ooPlaceInnerProduct() {
        return new OoPlaceInnerProduct();
    }

    public static VectorVectorOperation<Void> inPlaceVectorToVectorAddition() {
        return new InPlaceVectorToVectorAddition();
    }

    public static VectorVectorOperation<Vector> ooPlaceVectorToVectorAddition(Factory factory) {
        return new OoPlaceVectorToVectorAddition(factory);
    }

    public static VectorVectorOperation<Void> inPlaceVectorByVectorMultiplication() {
        return new InPlaceVectorByVectorMultiplication();
    }

    public static VectorOperation<Void> inPlaceVectorByValueMultiplication(double value) {
        return new InPlaceVectorByValueMultiplication(value);
    }

    public static VectorOperation<Vector> ooPlaceVectorByValueMultiplication(double value, Factory factory) {
        return new OoPlaceVectorByValueMultiplication(value, factory);
    }
}

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

package org.la4j.vector.operation.ooplace;

import org.la4j.factory.Factory;
import org.la4j.vector.Vector;
import org.la4j.iterator.VectorIterator;
import org.la4j.vector.Vectors;
import org.la4j.vector.dense.DenseVector;
import org.la4j.vector.operation.VectorVectorOperation;
import org.la4j.vector.sparse.SparseVector;

public class OoPlaceVectorToVectorAddition extends VectorVectorOperation<Vector> {

    private Factory factory;

    public OoPlaceVectorToVectorAddition(Factory factory) {
        this.factory = factory;
    }

    @Override
    public Vector apply(SparseVector a, SparseVector b) {
        VectorIterator these = a.nonZeroIterator();
        VectorIterator those = b.nonZeroIterator();

        Vector result = factory.createVector(a.length());

        VectorIterator both  = these.orElseAdd(those);

        while (both.hasNext()) {
            both.next();
            result.set(both.index(), both.value());
        }

        return result;
    }

    @Override
    public Vector apply(SparseVector a, DenseVector b) {
        return apply(b, a);
    }

    @Override
    public Vector apply(DenseVector a, DenseVector b) {
        Vector result = factory.createVector(a.length());
        for (int i = 0; i < a.length(); i++) {
            result.set(i, a.get(i) + b.get(i));
        }
        return result;
    }

    @Override
    public Vector apply(DenseVector a, SparseVector b) {
        Vector result = a.copy(factory);
        VectorIterator it = b.nonZeroIterator();
        while (it.hasNext()) {
            it.next();
            result.update(it.index(), Vectors.asPlusFunction(it.value()));
        }
        return result;
    }
}

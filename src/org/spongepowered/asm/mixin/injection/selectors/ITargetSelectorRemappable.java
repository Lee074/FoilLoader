/*
 * This file is part of Mixin, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.asm.mixin.injection.selectors;

import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;

/**
 * A target selector which can be remapped at compile time via an obfuscation
 * service
 */
public interface ITargetSelectorRemappable extends ITargetSelectorByName {
    
    /**
     * Returns this selector as a {@link MappingField} or
     * {@link MappingMethod}
     */
    IMapping<?> asMapping();

    /**
     * Returns this selector as a mapping method
     */
    MappingMethod asMethodMapping();
    
    /**
     * Returns this selector as a mapping field
     */
    MappingField asFieldMapping();
    
    /**
     * Create a new version of this member with a different owner
     * 
     * @param newOwner New owner for this member
     */
    ITargetSelectorRemappable move(String newOwner);
    
    /**
     * Create a new version of this member with a different descriptor
     * 
     * @param newDesc New descriptor for this member
     */
    ITargetSelectorRemappable transform(String newDesc);
    
    /**
     * Create a remapped version of this member using the supplied method data
     * 
     * @param srgMethod SRG method data to use
     * @param setOwner True to set the owner as well as the name
     * @return New MethodInfo with remapped values
     */
    ITargetSelectorRemappable remapUsing(MappingMethod srgMethod, boolean setOwner);
    
}

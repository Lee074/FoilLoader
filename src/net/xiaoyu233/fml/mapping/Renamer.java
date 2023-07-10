package net.xiaoyu233.fml.mapping;


import net.md_5.specialsource.RemappingClassAdapter;
import net.md_5.specialsource.repo.RuntimeRepo;
import net.xiaoyu233.fml.asm.IClassNameTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.service.ILegacyClassTransformer;

import javax.annotation.Nonnull;

public class Renamer implements IClassNameTransformer, ILegacyClassTransformer {
   private final Remapping remapping;

   public Renamer(Remapping remapping) {
      this.remapping = remapping;
   }

   public String unmapClassName(String name) {
      String unmap = this.remapping.unmap(name);
      return unmap != null ? unmap : name;
   }

   @Override
   public String getName() {
      return "Renamer";
   }

   @Override
   public boolean isDelegationExcluded() {
      return false;
   }

   public String remapClassName(String name) {
      String map = this.remapping.map(name);
      return map != null ? map.replace("/",".") : name;
   }

   public byte[] transform(String name, String transformedName, @Nonnull byte[] basicClass) {
      ClassReader reader = new ClassReader(basicClass);
      ClassNode source = new ClassNode();
      reader.accept(source, 0);
      ClassNode newClass = new ClassNode();
      RemappingClassAdapter mapper = new RemappingClassAdapter(newClass, this.remapping, RuntimeRepo.getInstance(),name);
      reader.accept(mapper, 0);
      ClassWriter wr = new ClassWriter(1);
      newClass.accept(wr);
      return wr.toByteArray();
   }

   @Override
   public byte[] transformClassBytes(String name, String transformedName, byte[] basicClass) {
      return this.transform(name, transformedName,basicClass);
   }
}

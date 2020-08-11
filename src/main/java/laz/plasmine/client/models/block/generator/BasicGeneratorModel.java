package laz.plasmine.client.models.block.generator;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import laz.plasmine.client.models.block.ITileModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BasicGeneratorModel extends Model implements ITileModel {
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer bone;

	public BasicGeneratorModel() {
		super(RenderType::getEntityTranslucentCull);
		textureWidth = 64;
		textureHeight = 64;

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone3.setTextureOffset(0, 38).addBox(-7.0F, -15.0F, -8.0F, 14.0F, 15.0F, 3.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone3.addChild(bone2);
		bone2.setTextureOffset(0, 0).addBox(-6.0F, -5.0F, -5.0F, 12.0F, 5.0F, 12.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -8.25F, 1.0F);
		bone3.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.7854F);
		bone.setTextureOffset(0, 17).addBox(-5.6768F, -5.5732F, -5.0F, 11.0F, 11.0F, 10.0F, 0.0F, false);
		bone.setTextureOffset(0, 0).addBox(-1.5F, -1.5F, -6.0F, 3.0F, 3.0F, 12.0F, 0.0F, false);
	}
	
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone3.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void rotate(Direction dir) {
		switch (dir.getIndex()) {
		case 2:
			bone3.rotateAngleY = 0;
			break;
		case 3:
			bone3.rotateAngleY = (float) Math.PI;
			break;
		case 4:
			bone3.rotateAngleY = (float) Math.PI / 2;
			break;
		case 5:
			bone3.rotateAngleY = -(float) Math.PI/2;
			break;
		}
	}

	@Override
	public void animation(int tick, float partialtick) {
		bone.rotateAngleZ = tick * (float)Math.PI * -0.1F * 0.3f;
	}

	@Override
	public void resetState() {
		bone.rotateAngleZ = 0;
	}
}
package laz.plasmine.client.models.block.convertor;
// Made with Blockbench 3.5.4

// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import laz.plasmine.client.models.block.ITileModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.Direction;

public class BasicConvertorModel extends Model implements ITileModel {
	private final ModelRenderer bone6;
	private final ModelRenderer rotation;
	private final ModelRenderer bone11;
	private final ModelRenderer bone9;
	private final ModelRenderer bone12;
	private final ModelRenderer bone8;
	private final ModelRenderer bone7;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer bone;

	public BasicConvertorModel() {
		super(RenderType::getEntityTranslucentCull);
		textureWidth = 128;
		textureHeight = 128;

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 16.0F, 0.0F);
		

		rotation = new ModelRenderer(this);
		rotation.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone6.addChild(rotation);
		setRotationAngle(rotation, 0.0F, 0.0F, 0.6109F);
		rotation.setTextureOffset(50, 73).addBox(-2.0F, -2.0F, -7.0F, 4.0F, 4.0F, 15.0F, 0.0F, false);
		rotation.setTextureOffset(27, 65).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 11.0F, 0.0F, false);
		rotation.setTextureOffset(0, 76).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 11.0F, 0.0F, false);
		rotation.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, 5.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 3.0F);
		rotation.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.0F, 1.3963F);
		bone11.setTextureOffset(0, 19).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 8.0F, 11.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 3.0F);
		rotation.addChild(bone9);
		setRotationAngle(bone9, 0.0F, 0.0F, 1.2217F);
		bone9.setTextureOffset(27, 8).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 8.0F, 11.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, 3.0F);
		rotation.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, 1.0472F);
		bone12.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 8.0F, 11.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 3.0F);
		rotation.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, 0.8727F);
		bone8.setTextureOffset(0, 38).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 8.0F, 11.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 3.0F);
		rotation.addChild(bone7);
		setRotationAngle(bone7, 0.0F, 0.0F, 0.6981F);
		bone7.setTextureOffset(27, 46).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 8.0F, 11.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 3.0F);
		rotation.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, 0.5236F);
		bone3.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 8.0F, 11.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 3.0F);
		rotation.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.3491F);
		bone2.setTextureOffset(54, 19).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 8.0F, 11.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 3.0F);
		rotation.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, 0.1745F);
		bone.setTextureOffset(0, 57).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 8.0F, 11.0F, 0.0F, false);

	}

	@Override
	public void rotate(Direction dir) {
		switch (dir.getIndex()) {
		case 2:
			bone6.rotateAngleY = 0;
			break;
		case 3:
			bone6.rotateAngleY = (float) Math.PI;
			break;
		case 4:
			bone6.rotateAngleY = (float) Math.PI / 2;
			break;
		case 5:
			bone6.rotateAngleY = -(float) Math.PI/2;
			break;
		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		bone6.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void animation(int tick, float partialTick) {
		rotation.rotateAngleZ = tick * (float) Math.PI * -0.1F * 0.3f;
	}

	@Override
	public void resetState() {
		rotation.rotateAngleZ = 0;
	}
}